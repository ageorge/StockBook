package com.stock.model;

import java.io.Serializable;
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
 * Entity class that stores the User details
 * @author anitageorge and bhavya
 *
 */
@Entity
@Table(name="user", uniqueConstraints= {@UniqueConstraint(columnNames = {"userid","user_name"})})
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int userid;
	
	@Column
	private String user_name;
	
	@Column
	private String name;
	
	@Column
	private String ssn;
	
	@Column
	private String password;
	
	@Column
	private String account_status;
	
	@Column
	private String role;
	
	@OneToMany(mappedBy="user")
	private Set<Post> posts;
	
	@OneToMany(mappedBy="user")
	private Set<Reply> replies;
	
	/**
	 * @return the replies
	 */
	public Set<Reply> getReplies() {
		return replies;
	}
	/**
	 * @param replies the replies to set
	 */
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the account_status
	 */
	public String getAccount_status() {
		return account_status;
	}
	/**
	 * @param account_status the account_status to set
	 */
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userid=" + userid + ", user_name=" + user_name + ", role=" + role + "]";
	}
	
}
