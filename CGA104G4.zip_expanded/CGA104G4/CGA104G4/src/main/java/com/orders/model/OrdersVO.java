package com.orders.model;

import java.sql.Date;

public class OrdersVO implements java.io.Serializable{
	private Integer ordId;
	private Integer memId;
	private Integer storeId;
	private Integer ordAmt;
	private Integer ordStat;
	private Date ordTime;
	
	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getOrdAmt() {
		return ordAmt;
	}
	public void setOrdAmt(Integer ordAmt) {
		this.ordAmt = ordAmt;
	}
	public Integer getOrdStat() {
		return ordStat;
	}
	public void setOrdStat(Integer ordStat) {
		this.ordStat = ordStat;
	}
	public Date getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Date ordTime) {
		this.ordTime = ordTime;
		
	}
}
