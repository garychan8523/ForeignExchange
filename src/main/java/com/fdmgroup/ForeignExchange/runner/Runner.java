package com.fdmgroup.ForeignExchange.runner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Runner {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("foreign_exchange_config");

		
		
		emf.close();
		
	}

}
