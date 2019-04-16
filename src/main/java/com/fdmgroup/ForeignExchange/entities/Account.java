package com.fdmgroup.ForeignExchange.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity

@Table(name = "fx_account")
public class Account {

	@EmbeddedId
	private AccountKey accountKey;

	@MapsId("AccountKey")
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;

	@MapsId("AccountKey")
	@ManyToOne
	@JoinColumn(name = "currencyId")
	private Currency currency;

	@Column(nullable = false)
	@Check(constraints = "balance >= 0")
	private Double balance;

	public Account() {
		super();
	}

	public Account(User user, Currency currency, Double balance) {
		super();
		accountKey = new AccountKey();
		accountKey.setUserId(user.getUserId());
		accountKey.setCurrencyId(currency.getCurrencyId());
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountKey getAccountKey() {
		return accountKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountKey == null) ? 0 : accountKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountKey == null) {
			if (other.accountKey != null)
				return false;
		} else if (!accountKey.equals(other.accountKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountKey=" + accountKey + ", user=" + user + ", currency=" + currency + ", balance="
				+ balance + "]";
	}

}
