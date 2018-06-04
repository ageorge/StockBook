package com.stock.service;

import java.util.List;

import com.stock.model.Stock;

/**
 * Interface to process stock interaction with the database
 * @author anitageorge and bhavya
 *
 */
public interface StockService {
	public List<Stock> getStocks();
	public Stock getStock(int stockid);
	public List<String> getStockNames();
	public List<Integer> getStock(String stock_name);
}
