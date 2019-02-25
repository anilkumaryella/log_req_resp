package com.altimetrik.poc.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "AUDIT_MASTER")
@IdClass(AuditPK.class)
public class AuditEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "API_NAME")
	private String apiName;

	@Column(name = "QUERY_PARAM")
	private String queryParam;

	@Column(name = "API_URI")
	private String apiUrI;

	@Column(name = "REQUEST_CONTENT_TYPE")
	private String requestContentType;

	@Column(name = "RESPONSE_CONTENT_TYPE")
	private String respContentType;

	@Column(name = "CLIENT_ADDR")
	private String clientAddr;

	@Column(name = "HOST_ADDR")
	private String hostAddr;


	@Column(name = "HTTP_METHOD")
	private String httpMethod;

	@Column(name = "HTTP_HEADERS")
	private String httpHeaders;

	@Column(name = "REQUEST_BODY")
	private String requestBody;

	@Column(name = "RESPONSE_BODY")
	private String responseBody;

	@Column(name = "HTTP_STATUS")
	private String status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTER_DATE_TIME", nullable = false)
	private Date registerDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public String getApiUrI() {
		return apiUrI;
	}

	public void setApiUrI(String apiUrI) {
		this.apiUrI = apiUrI;
	}

	public String getRequestContentType() {
		return requestContentType;
	}

	public void setRequestContentType(String requestContentType) {
		this.requestContentType = requestContentType;
	}

	public String getRespContentType() {
		return respContentType;
	}

	public void setRespContentType(String respContentType) {
		this.respContentType = respContentType;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public String getHostAddr() {
		return hostAddr;
	}

	public void setHostAddr(String hostAddr) {
		this.hostAddr = hostAddr;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(String httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegisterDateTime() {
		return registerDateTime;
	}

	public void setRegisterDateTime(Date registerDateTime) {
		this.registerDateTime = registerDateTime;
	}

	@Override
	public String toString() {
		return "AuditEntity [id=" + id + ", apiName=" + apiName + ", queryParam=" + queryParam + ", apiUrI=" + apiUrI
				+ ", requestContentType=" + requestContentType + ", respContentType=" + respContentType
				+ ", clientAddr=" + clientAddr + ", hostAddr=" + hostAddr + ", httpMethod=" + httpMethod
				+ ", httpHeaders=" + httpHeaders + ", requestBody=" + requestBody + ", responseBody=" + responseBody
				+ ", status=" + status + ", registerDateTime=" + registerDateTime + "]";
	}

}
