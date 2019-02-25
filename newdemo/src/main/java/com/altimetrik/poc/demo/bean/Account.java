package com.altimetrik.poc.demo.bean;

import java.math.BigDecimal;

public class Account {

	
	private String mobileNo;

	private String accountNo;

	private BigDecimal bal;

	private String status;

	

	public BigDecimal getBal() {
		return bal;
	}

	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [ mobileNo=" + mobileNo + ", accountNo=" + accountNo + ", bal=" + bal
				+ ", status=" + status + "]";
	}

	

}
