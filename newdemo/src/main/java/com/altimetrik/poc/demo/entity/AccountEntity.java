package com.altimetrik.poc.demo.entity;

import java.math.BigDecimal;
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
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="ACCOUNT_MASTER")
@IdClass(AccountPK.class)
public class AccountEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="ACCOUNT_NO")
	private String accountNo;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="BALANCE")
	private BigDecimal bal;

	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTER_DATE_TIME", nullable = false)
    private Date registerDateTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE_TIME", nullable = false)
    private Date updatedDateTime;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBal() {
		return bal;
	}

	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}

	public Date getRegisterDateTime() {
		return registerDateTime;
	}

	public void setRegisterDateTime(Date registerDateTime) {
		this.registerDateTime = registerDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
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
	
}
