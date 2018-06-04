package com.stock.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stock.dao.StockDao;
import com.stock.model.Stock;

/**
 * 
 * Class that implements the StockDao interface
 * @author anitageorge and bhavya
 *
 */
@Repository
@Transactional
public class StockDaoImpl implements StockDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Method to retrieve all stocks from the database
	 */
	@Override
	public List<Stock> getStocks() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Stock>) session.createQuery("from Stock").list();
	}

	/**
	 * Method to retrieve a particular post from the database based on stockid
	 */
	@Override
	public Stock getStock(int stockid) {
		Session session = sessionFactory.getCurrentSession();
		Stock stock = session.get(Stock.class, stockid);
		return stock;
	}

	/**
	 * Method to retrieve all the unique names of the stock
	 */
	@Override
	public List<String> getStockNames() {
		Session session = sessionFactory.getCurrentSession();
		List<Stock> list = session.createQuery("from Stock").list();
		List<String> stocknames = new ArrayList<>();
		for(Stock stock : list) {
			if(!stocknames.contains(stock.getStock_name())) {
				stocknames.add(stock.getStock_name());
			}
		}
		return stocknames;
	}

	/**
	 * Method to retrieve all stock details based on stock name
	 */
	@Override
	public List<Integer> getStock(String stock_name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stock as s where s.stock_name = :stockname");
		query.setParameter("stockname", stock_name);
		List<Stock> stocks = query.getResultList();
		List<Integer> stockids = new ArrayList<>();
		for(Stock stock : stocks) {
			stockids.add(stock.getStockid());
		}
		
		return stockids;
	}

}
