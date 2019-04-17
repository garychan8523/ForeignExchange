package com.fdmgroup.ForeignExchange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.ForeignExchange.dal.AccountDaoImpl;
import com.fdmgroup.ForeignExchange.dal.CurrencyDaoImpl;
import com.fdmgroup.ForeignExchange.dal.OrderDaoImpl;
import com.fdmgroup.ForeignExchange.dal.TradeDaoImpl;
import com.fdmgroup.ForeignExchange.dal.UserDaoImpl;

@Component
public class IndexService {
@Autowired
private OrderDaoImpl orderDaoImpl;
@Autowired
private AccountDaoImpl accountDaoImpl;
@Autowired
private UserDaoImpl userDaoImpl;
@Autowired
private CurrencyDaoImpl currencyDaoImpl;
@Autowired
private TradeDaoImpl tradeDaoImpl;

}
