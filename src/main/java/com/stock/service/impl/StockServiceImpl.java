package com.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.StockDao;
import com.stock.model.Stock;
import com.stock.service.StockService;

/**
 * Class implementing the StockService interface 
 * @author anitageorge and bhavya
 *
 */
@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockDao stockDao;

	/**
	 * Retrieves all stocks from the DAO layer
	 */
	@Override
	public List<Stock> getStocks() {
		List<Stock> stocks = stockDao.getStocks();
		
		return stocks;
	}

	/**
	 * Retrieves the stocks based on id from the DAO layer
	 */
	@Override
	public Stock getStock(int stockid) {
		return stockDao.getStock(stockid);
	}

	/**
	 * Retrieves the names of the stocks based from the DAO layer
	 */
	@Override
	public List<String> getStockNames() {
		return stockDao.getStockNames();
	}

	/**
	 * Retrieves the id of the stocks based from the DAO layer
	 */
	@Override
	public List<Integer> getStock(String stock_name) {
		return stockDao.getStock(stock_name);
	}

}
