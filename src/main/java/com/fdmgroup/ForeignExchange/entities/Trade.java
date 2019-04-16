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

@GenericGenerator(name = "uuid_trade", strategy = "uuid")

@Table(name = "fx_trade")
public class Trade {

	@Id
	@GeneratedValue(generator = "uuid_trade")
	@Column(length = 36)
	private String tradeId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "orderId", nullable = false, updatable = false)
	private Order order;

	@Column(nullable = false, updatable = false)
	@Check(constraints = "amount >= 0")
	private Double amount;

	@Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	private String timestamp;

	public Trade() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTradeId() {
		return tradeId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public static Comparator<Trade> sortByTimestampDesc = new Comparator<Trade>() {

		public int compare(Trade t1, Trade t2) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date t1date = null;
			Date t2date = null;
			try {
				t1date = format.parse(t1.getTimestamp());
				t2date = format.parse(t2.getTimestamp());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			return t2date.compareTo(t1date);
		}
	};

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", user=" + user + ", order=" + order + ", amount=" + amount
				+ ", timestamp=" + timestamp + "]";
	}

}
