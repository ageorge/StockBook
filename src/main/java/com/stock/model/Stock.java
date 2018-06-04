package com.stock.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity class that stores the stock details
 * @author anitageorge and bhavya
 *
 */
@Entity
@Table(name="stock", uniqueConstraints= {@UniqueConstraint(columnNames = {"stockid"})})
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int stockid;
	
	@Column
	private String stock_name;
	
	@Column
	private Date stock_date;
	
	@Column
	private Double stock_price;

	@OneToMany(mappedBy="stock")
    private Set<Post> posts;
	
	/**
	 * @return the posts
	 */
	public Set<Post> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	/**
	 * @return the stockid
	 */
	public int getStockid() {
		return stockid;
	}

	/**
	 * @param stockid the stockid to set
	 */
	public void setStockid(int stockid) {
		this.stockid = stockid;
	}

	/**
	 * @return the stock_name
	 */
	public String getStock_name() {
		return stock_name;
	}

	/**
	 * @param stock_name the stock_name to set
	 */
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

	/**
	 * @return the stock_date
	 */
	public Date getStock_date() {
		return stock_date;
	}

	/**
	 * @param stock_date the stock_date to set
	 */
	public void setStock_date(Date stock_date) {
		this.stock_date = stock_date;
	}

	/**
	 * @return the stock_price
	 */
	public Double getStock_price() {
		return stock_price;
	}

	/**
	 * @param stock_price the stock_price to set
	 */
	public void setStock_price(Double stock_price) {
		this.stock_price = stock_price;
	}

//	/**
//	 * @return the posts
//	 */
//	public Set<Post> getPosts() {
//		return posts;
//	}
//
//	/**
//	 * @param posts the posts to set
//	 */
//	public void setPosts(Set<Post> posts) {
//		this.posts = posts;
//	}
	
	@Override
	public String toString() {
		return stockid + "-" + stock_name;
	}
}
