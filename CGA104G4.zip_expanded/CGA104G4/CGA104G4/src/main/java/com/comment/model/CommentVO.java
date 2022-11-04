package com.comment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentVO implements Serializable {
	private Integer comtId;
	private Integer memId;
	private Integer storeId;
	private Integer comtType;
	private String comtCont;
	private Integer comtVal;
	private Timestamp comtTime;
	private Integer comtStat;

	public CommentVO() {
		super();
	}

	public Integer getComtId() {
		return comtId;
	}

	public void setComtId(Integer comtId) {
		this.comtId = comtId;
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

	public Integer getComtType() {
		return comtType;
	}

	public void setComtType(Integer comtType) {
		this.comtType = comtType;
	}

	public String getComtCont() {
		return comtCont;
	}

	public void setComtCont(String comtCont) {
		this.comtCont = comtCont;
	}

	public Integer getComtVal() {
		return comtVal;
	}

	public void setComtVal(Integer comtVal) {
		this.comtVal = comtVal;
	}

	public Timestamp getComtTime() {
		return comtTime;
	}

	public void setComtTime(Timestamp comtTime) {
		this.comtTime = comtTime;
	}

	public Integer getComtStat() {
		return comtStat;
	}

	public void setComtStat(Integer comtStat) {
		this.comtStat = comtStat;
	}

	@Override
	public String toString() {
		return "CommentVO [comtId=" + comtId + ", memId=" + memId + ", storeId=" + storeId + ", comtType=" + comtType
				+ ", comtCont=" + comtCont + ", comtVal=" + comtVal + ", comtTime=" + comtTime + ", comtStat="
				+ comtStat + "]";
	}

}
