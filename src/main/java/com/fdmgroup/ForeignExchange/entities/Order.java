package com.fdmgroup.ForeignExchange.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

@Entity

@GenericGenerator(name = "uuid_order", strategy = "uuid")

@Table(name = "fx_order")
public class Order {

	public static enum Type {
		BUY, SELL
	}

	public static enum Status {
		ACTIVE, CANCELLED, COMPLETED
	}

	@Id
	@GeneratedValue(generator = "uuid_order")
	@Column(length = 36)
	private String orderId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false, updatable = false)
	private User user;

	@Column(nullable = false, updatable = false)
	private Type type;

	@ManyToOne
	@JoinColumn(nullable = false, updatable = false)
	private Currency currencyBuy;

	@ManyToOne
	@JoinColumn(nullable = false, updatable = false)
	private Currency currencySell;

	@Column(nullable = false, updatable = false)
	@Check(constraints = "originalAmount >= 0")
	private Double originalAmount;

	@Column(nullable = false)
	@Check(constraints = "currentAmount >= 0")
	private Double currentAmount;

	@Column(nullable = false, updatable = false)
	@Check(constraints = "rate >= 0")
	private Double rate;

	@Column(name = "orderTime", nullable = false, updatable = false, insertable = false, columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	private String orderTime;

	@Column(nullable = false)
	private Status status;

	public Order() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Currency getCurrencyBuy() {
		return currencyBuy;
	}

	public void setCurrencyBuy(Currency currencyBuy) {
		this.currencyBuy = currencyBuy;
	}

	public Currency getCurrencySell() {
		return currencySell;
	}

	public void setCurrencySell(Currency currencySell) {
		this.currencySell = currencySell;
	}

	public Double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public static Comparator<Order> sortByOrderTimeDesc = new Comparator<Order>() {

		public int compare(Order b1, Order b2) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date b1date = null;
			Date b2date = null;
			try {
				b1date = format.parse(b1.getOrderTime());
				b2date = format.parse(b2.getOrderTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return b2date.compareTo(b1date);
		}
	};

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderBook [orderId=" + orderId + ", user=" + user + ", type=" + type + ", currencyBuy=" + currencyBuy
				+ ", currencySell=" + currencySell + ", originalAmount=" + originalAmount + ", currentAmount="
				+ currentAmount + ", rate=" + rate + ", orderTime=" + orderTime + ", status=" + status + "]";
	}

}
