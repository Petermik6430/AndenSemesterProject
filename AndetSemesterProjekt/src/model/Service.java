package model;

import java.util.Objects;

public class Service {

	private int serviceId;
	private String name;
	private int duration;
	//private double price;

	public Service(int serviceId, String name, int duration) {
	this.serviceId = serviceId;
	this.name = name;
	this.duration = duration;
	
	}


	public Service() {
		// TODO Auto-generated constructor stub
	}



	public int getServiceId() {
		return serviceId;
	}
	public String getName() {
		return name;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	public void setName(String name) {
		this.name = name;	
	}
	
	//public void setPrice(double price) {
	//	this.price = price;
	//}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	//public double getPrice() {
	//	return price;
	//}

	@Override
	public String toString() {
		return name;
	}

	
}
