package com.altimetrik.poc.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.altimetrik.poc.demo.bean.Account;
import com.altimetrik.poc.demo.bean.Response_VO;
import com.altimetrik.poc.demo.dao.AccountDao;
import com.altimetrik.poc.demo.dao.SearchCriteria;
import com.altimetrik.poc.demo.exception.DataException;
import com.altimetrik.poc.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Inject
	private AccountDao accDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Override
	public <T> ResponseEntity<T> search(String type, String param) throws DataException {
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		LOGGER.info("Search all [{}] with criteria [{}]", type, param);

		if (param != null) {
			Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
			Matcher matcher = pattern.matcher(param + ",");
			while (matcher.find()) {
				params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
			}
		}
		ResponseEntity<T> e = null;
		if (StringUtils.equals(type, "account")) {

			e = new ResponseEntity<T>((T) accDao.searchUser(params), HttpStatus.OK);
		} else if (StringUtils.equals(type, "audit")) {

			e = new ResponseEntity<T>((T) accDao.searchAuditData(params), HttpStatus.OK);
		} else
			throw new DataException("11", "Search Type is not found !");
		return e;
	}

	@Override
	public ResponseEntity<Response_VO> addAcc(Account account) {
		Response_VO response = new Response_VO("11", "Failure");
		if (accDao.addAcc(account) != null) {
			response.setResponseCode("00");
			response.setResponseMsg("Success !");
			;
		}
		return new ResponseEntity<Response_VO>(response, HttpStatus.OK);
	}
}
