package com.fdmgroup.ForeignExchange.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity

@GenericGenerator(name = "uuid_currency", strategy = "uuid")

@Table(name = "fx_currency")
public class Currency {

	@Id
	@GeneratedValue(generator = "uuid_currency")
	@Column(length = 36)
	private String currencyId;

	@Column(unique = true, nullable = false)
	private String currencyName;

	@Column(unique = true, nullable = false)
	private String currencyCode;

	@Column
	private String symbol;

	public Currency() {
		super();
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((currencyName == null) ? 0 : currencyName.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Currency other = (Currency) obj;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (currencyName == null) {
			if (other.currencyName != null)
				return false;
		} else if (!currencyName.equals(other.currencyName))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Currency [currencyId=" + currencyId + ", currencyName=" + currencyName + ", currencyCode="
				+ currencyCode + ", symbol=" + symbol + "]";
	}

}
