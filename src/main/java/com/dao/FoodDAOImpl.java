package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Food;
import com.util.HibernateUtil;



public class FoodDAOImpl implements FoodDAO {
	@Override
	public void insert(Food food) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(food);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void update(Food food) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(food);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Integer foodId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Food findByPrimaryKey(Integer foodId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Food food = null;
		try {
			session.beginTransaction();
			food = session.get(Food.class, foodId);
			session.getTransaction().commit();
			return food;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Food> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Food> list = session.createQuery("FROM Food", Food.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
}
