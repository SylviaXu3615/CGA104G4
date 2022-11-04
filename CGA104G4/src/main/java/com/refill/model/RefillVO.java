package com.refill.model;

import java.io.Serializable;
import java.sql.Date;

public class RefillVO implements Serializable {
	private Integer refillId;
	private Integer memId;
	private Date refillTime;
	private Integer refillAmt;
	private Integer refillToken;

	public RefillVO() {

	}

	public Integer getRefillId() {
		return refillId;
	}

	public void setRefillId(Integer refillId) {
		this.refillId = refillId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Date getRefillTime() {
		return refillTime;
	}

	public void setRefillTime(Date refillTime) {
		this.refillTime = refillTime;
	}

	public Integer getRefillAmt() {
		return refillAmt;
	}

	public void setRefillAmt(Integer refillAmt) {
		this.refillAmt = refillAmt;
	}

	public Integer getRefillToken() {
		return refillToken;
	}

	public void setRefillToken(Integer refillToken) {
		this.refillToken = refillToken;
	}

}
