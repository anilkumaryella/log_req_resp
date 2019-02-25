package com.altimetrik.poc.demo.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.poc.demo.bean.Account;
import com.altimetrik.poc.demo.bean.Response_VO;
import com.altimetrik.poc.demo.common.Versions;
import com.altimetrik.poc.demo.exception.DataException;
import com.altimetrik.poc.demo.service.AccountService;

@RestController
@RequestMapping(value = "/app/demo/", consumes = Versions.V1_0, produces = Versions.V1_0)
public class AccountController {

	@Inject
	private AccountService accService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	// To Search registered accounts or audit table depending on the input parameters
	@Cacheable(value="searchCache")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public <T> ResponseEntity<T> searchAcc(@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "param", required = false) String param) throws DataException {

		return accService.search(type, param);

	}

	//To Register/Add a new Account 
	@RequestMapping(value = "/registeraccount", method = RequestMethod.POST)
	public ResponseEntity<Response_VO> addAcc(@RequestBody Account account) throws DataException {
		LOGGER.info("add account [{}]", account);

		return accService.addAcc(account);

	}

}
