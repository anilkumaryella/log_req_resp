package com.altimetrik.poc.demo.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.altimetrik.poc.demo.entity.AuditEntity;
import com.altimetrik.poc.demo.entity.repo.AuditRepo;

@Component
public class LoggingFilter extends OncePerRequestFilter {

	@Inject
	private AuditRepo repo;

	private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(MediaType.valueOf("text/*"),
			MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.valueOf("application/*+json"), MediaType.valueOf("application/*+xml"),
			MediaType.MULTIPART_FORM_DATA);

	Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isAsyncDispatch(request)) {
			filterChain.doFilter(request, response);
		} else {
			doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
		}
	}

	protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response,
			FilterChain filterChain) throws ServletException, IOException {

		AuditEntity audit = new AuditEntity();
		try {
			String queryString = request.getQueryString();
			if (queryString != null) {
				audit.setApiName(queryString);
			}
			audit.setHttpMethod(request.getMethod());
			audit.setApiUrI(request.getRequestURI());
			String[] s = audit.getApiUrI().split("/");
			audit.setApiName(s[s.length - 1]);
			audit.setQueryParam(request.getParameterMap().toString());
			audit.setRequestContentType(request.getContentType());

			audit.setHostAddr(request.getLocalAddr());

			audit.setClientAddr(request.getRemoteAddr());

			StringBuffer sb = new StringBuffer();

			List<String> l = Collections.list(request.getHeaderNames());
			// .forEach(headerName ->
			// Collections.list(request.getHeaders(headerName)));
			for (String h : l) {
				sb.append(h + " = " + request.getHeader(h) + ",");
			}

			audit.setHttpHeaders(sb.toString());
			filterChain.doFilter(request, response);
		} finally {
			byte[] content = request.getContentAsByteArray();
			if (content.length > 0) {
				audit.setRequestBody(logContent(content, request.getContentType(), request.getCharacterEncoding()));
			}

			audit.setRespContentType(response.getContentType());
			int status = response.getStatus();

			audit.setStatus(status + "");
			byte[] respContent = response.getContentAsByteArray();
			if (respContent.length > 0) {
				audit.setResponseBody(
						logContent(respContent, response.getContentType(), response.getCharacterEncoding()));
			}

			response.copyBodyToResponse();
			log.info("audit [{}]", audit);
			if (audit.getHttpHeaders() != null && audit.getHttpHeaders().length() > 254) {
				audit.setHttpHeaders(audit.getHttpHeaders().substring(0, 254));
			}
			if (audit.getResponseBody() != null && audit.getResponseBody().length() > 254) {
				audit.setResponseBody(audit.getResponseBody().substring(0, 254));
			}
			repo.save(audit);
		}
	}

	private String logContent(byte[] content, String contentType, String contentEncoding) {
		MediaType mediaType = MediaType.valueOf(contentType);
		boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
		String body = "";
		if (visible) {
			try {
				body = new String(content, contentEncoding);
			} catch (UnsupportedEncodingException e) {
				log.info("[{} bytes content]", content.length);
			}
		} else {

			log.info("[{} bytes content]", content.length);
		}
		return body;
	}

	private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
		if (request instanceof ContentCachingRequestWrapper) {
			return (ContentCachingRequestWrapper) request;
		} else {
			return new ContentCachingRequestWrapper(request);
		}
	}

	private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper) {
			return (ContentCachingResponseWrapper) response;
		} else {
			return new ContentCachingResponseWrapper(response);
		}
	}
}
