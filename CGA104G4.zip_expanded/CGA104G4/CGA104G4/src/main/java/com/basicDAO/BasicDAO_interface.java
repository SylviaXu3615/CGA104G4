package com.basicDAO;

import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface BasicDAO_interface<T> {
	public void insert(T VO);

	public void update(T VO);

	public void delete(Integer tableId);

	public T findByPrimaryKey(Integer tableId);

	public List<T> getAll();

	default Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	default Transaction beginTransaction(){
		return getSession().beginTransaction();
	}

	default void commit(){
		getSession().getTransaction().commit();
	}

	default void rollback(){
		getSession().getTransaction().rollback();
	}

}
