package com.stock.dao;

import java.util.List;

import com.stock.model.Stock;

/**
 * Interface for interacting with the stock table in database
 * @author anitageorge and bhavya
 *
 */
public interface StockDao {
	public List<Stock> getStocks();
	public Stock getStock(int stockid);
	public List<String> getStockNames();
	public List<Integer> getStock(String stock_name);
}
