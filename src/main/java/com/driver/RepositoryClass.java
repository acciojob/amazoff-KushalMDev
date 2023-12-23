package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class RepositoryClass {

	HashMap<String, Order> orderMapping = new HashMap<>();
	HashMap<String, ArrayList<Order>> deliveryPartnerOrderMapping = new HashMap<>();
	HashMap<String, DeliveryPartner> deliveryPartner = new HashMap<>();
	HashMap<String, Order> unassignedorderMapping = new HashMap<>();

	public void saveOrderMapping(Order order) {
		// TODO Auto-generated method stub
		if(order.getId().length()!=0 && order.getDeliveryTime()!=0)
		{orderMapping.put(order.getId(), order);
		unassignedorderMapping.put(order.getId(), order);}
	}

	public void addPartner(String partnerId) {
		// TODO Auto-generated method stub
		if(partnerId.length()!=0)
			deliveryPartner.put(partnerId, new DeliveryPartner(partnerId));
	}

	public void addOrderPartnerPair(String orderId, String partnerId) {
		// TODO Auto-generated method stub
		if(!orderMapping.containsKey(orderId) || !deliveryPartner.containsKey(partnerId)){
			return;
		}
		ArrayList<Order> list = deliveryPartnerOrderMapping.getOrDefault(partnerId, new ArrayList<>());
		for (String key : orderMapping.keySet()) {
			if (key.equals(orderId)) {
				list.add(orderMapping.get(key));
				break;
			}
		}
		deliveryPartnerOrderMapping.put(partnerId, list);
		DeliveryPartner dp = deliveryPartner.get(partnerId);
		int number = dp.getNumberOfOrders();
		number++;
		dp.setNumberOfOrders(number);
		unassignedorderMapping.remove(orderId);

	}

	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		if(orderMapping.containsKey(orderId))
			return orderMapping.get(orderId);
		return null;
	}

	public DeliveryPartner getPartnerById(String partnerId) {
		// TODO Auto-generated method stub
		if(deliveryPartner.containsKey(partnerId))
			return deliveryPartner.get(partnerId);
		return null;
	}

	public Integer getOrderCountByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		if(!deliveryPartner.containsKey(partnerId)){
			return 0;
		}
		if(!(deliveryPartnerOrderMapping.containsKey(partnerId))){
			deliveryPartnerOrderMapping.put(partnerId, new ArrayList<>());
		}
		return deliveryPartnerOrderMapping.get(partnerId).size();
	}

	public List<String> getOrdersByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		if(!deliveryPartner.containsKey(partnerId)){
			return new ArrayList<>();
		}
		List<String> result = new ArrayList<>();
		ArrayList<Order> list = deliveryPartnerOrderMapping.getOrDefault(partnerId,new ArrayList<>());
		for (Order order : list) {
			result.add(order.getId());
		}
		return result;
	}

	public List<String> getAllOrders() {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<>();

		for (String key : orderMapping.keySet()) {
			result.add(key);
		}

		return result;
	}

	public Integer getCountOfUnassignedOrders() {
		// TODO Auto-generated method stub
		List<String> assignedOrders = new ArrayList<>();
		for (String key : deliveryPartnerOrderMapping.keySet()) {
			for (Order order : deliveryPartnerOrderMapping.get(key)) {
				assignedOrders.add(order.getId());
			}
		}
		int count = 0;
		for (String key : orderMapping.keySet()) {
			if (!(assignedOrders.contains(key))) {
				count++;
			}
		}

		return count;
	}

	public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
		// TODO Auto-generated method stub
		int count=0;
		
		int giventime=0;
		 if(time.length() != 0){
	            String hrs = time.substring(0, 2);
	            String mnts = time.substring(3);
	            giventime = Integer.parseInt(hrs)*60 + Integer.parseInt(mnts);
	        }
		else{
			return 0;
		}
		 for (Order order : deliveryPartnerOrderMapping.get(partnerId)) {
				int orderTime=order.getDeliveryTime();
				if(orderTime>giventime) {
					count++;
				}
			}
		return count;
	}

	public String getLastDeliveryTimeByPartnerId(String partnerId) {
		// TODO Auto-generated method stub
		if(!deliveryPartner.containsKey(partnerId)){
			return null;
		}
		int time=0;
		for (Order order : deliveryPartnerOrderMapping.get(partnerId)) {
			int deliveryTime=order.getDeliveryTime();
			time=Math.max(time,deliveryTime);
		}
		int hours=time/60;
		int minutes=time-(hours*60);
		String hh,mm;
		if(hours<10) {
			hh="0"+""+hours;
		}
		else {
			hh=""+hours;
		}
		if(minutes<10) {
			mm="0"+""+minutes;
		}
		else {
			mm=""+minutes;
		}
		
		return hh+":"+mm;
	}

	public void deletePartnerById(String partnerId) {
		// TODO Auto-generated method stub
			if(!deliveryPartner.containsKey(partnerId)){
			return ;
		}
		for(Order order:deliveryPartnerOrderMapping.get(partnerId)) {
			unassignedorderMapping.put(order.getId(), order);
		}
		deliveryPartnerOrderMapping.remove(partnerId);
		deliveryPartner.remove(partnerId);
	}

	public void deleteOrderById(String orderId) {
		// TODO Auto-generated method stub
		if(!orderMapping.containsKey(orderId)){
			return;
		}
		orderMapping.remove(orderId);
		unassignedorderMapping.remove(orderId);
		for (String key : deliveryPartnerOrderMapping.keySet()) {
			ArrayList<Order> list=deliveryPartnerOrderMapping.get(key);
			for(Order order:list) {
				if(order.getId().equals(orderId)) {
					list.remove(order);
					DeliveryPartner dp=deliveryPartner.get(key);
					int numberOfOrders=dp.getNumberOfOrders();
					numberOfOrders--;
					dp.setNumberOfOrders(numberOfOrders);
					break;
				}
			}
		}
	}



}
