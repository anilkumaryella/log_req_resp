package com.altimetrik.poc.demo.service;

import org.springframework.http.ResponseEntity;

import com.altimetrik.poc.demo.bean.Account;
import com.altimetrik.poc.demo.bean.Response_VO;
import com.altimetrik.poc.demo.exception.DataException;

public interface AccountService {

	<T> ResponseEntity<T>  search(String type, String param) throws DataException;

	ResponseEntity<Response_VO> addAcc(Account account);

}
