package com.promo.model;

import java.io.Serializable;
import java.sql.Date;

public class PromoVO implements Serializable {
	private Integer promoId;
	private String promoCont;
	private Date promoTimeS;
	private Date promoTimeE;
	private Integer promoVal;

	public PromoVO() {
		super();
	}

	public Integer getPromoId() {
		return promoId;
	}

	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
	}

	public String getPromoCont() {
		return promoCont;
	}

	public void setPromoCont(String promoCont) {
		this.promoCont = promoCont;
	}

	public Date getPromoTimeS() {
		return promoTimeS;
	}

	public void setPromoTimeS(Date promoTimeS) {
		this.promoTimeS = promoTimeS;
	}

	public Date getPromoTimeE() {
		return promoTimeE;
	}

	public void setPromoTimeE(Date promoTimeE) {
		this.promoTimeE = promoTimeE;
	}

	public Integer getPromoVal() {
		return promoVal;
	}

	public void setPromoVal(Integer promoVal) {
		this.promoVal = promoVal;
	}

	@Override
	public String toString() {
		return "PromoVO [promoId=" + promoId + ", promoCont=" + promoCont + ", promoTimeS=" + promoTimeS
				+ ", promoTimeE=" + promoTimeE + ", promoVal=" + promoVal + "]";
	}

}
