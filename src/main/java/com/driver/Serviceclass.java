package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Serviceclass {
	//@Autowired
	RepositoryClass repositoryClass= new RepositoryClass();

	public void saveOrderMapping(Order order) {
		// TODO Auto-generated method stub
		repositoryClass.saveOrderMapping(order);
	}

	public void addPartner(String partnerId) {
		// TODO Auto-generated method stub
		repositoryClass.addPartner(partnerId);
	}

	public void addOrderPartnerPair(String orderId, String partnerId) {
		// TODO Auto-generated method stub
		repositoryClass.addOrderPartnerPair(orderId, partnerId);
	}

	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		return repositoryClass.getOrderById(orderId);
	}

	public DeliveryPartner getPartnerById(String partnerId) {
		// TODO Auto-generated method stub
		return repositoryClass.getPartnerById(partnerId);
	}

	public Integer getOrderCountByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		return repositoryClass.getOrderCountByPartnerId(partnerId);
	}

	public List<String> getOrdersByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		return repositoryClass.getOrdersByPartnerId(partnerId);
	}

	public List<String> getAllOrders() {
		// TODO Auto-generated method stub
		return repositoryClass.getAllOrders();
	}

	public Integer getCountOfUnassignedOrders() {
		// TODO Auto-generated method stub
		return repositoryClass.getCountOfUnassignedOrders();
	}

	public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
		// TODO Auto-generated method stub
		return repositoryClass.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
	}

	public String getLastDeliveryTimeByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		return repositoryClass.getLastDeliveryTimeByPartnerId(partnerId);
	}

	public void deletePartnerById(String partnerId) {
		// TODO Auto-generated method stub
		repositoryClass.deletePartnerById(partnerId);
	}

	public void deleteOrderById(String orderId) {
		// TODO Auto-generated method stub
		repositoryClass.deleteOrderById(orderId);
	}

}
