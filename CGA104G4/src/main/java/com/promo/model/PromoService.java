package com.promo.model;

import java.util.List;

public class PromoService {
	PromoDAO_interface dao = new PromoDAO();

	public List<PromoVO> getAll() {

		return dao.getAll();
	}

}
