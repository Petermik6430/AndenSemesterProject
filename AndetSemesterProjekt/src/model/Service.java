package model;

import java.util.Objects;

public class Service {

	private int serviceId;
	private String name;
	private int duration;
	//private double price;
	
	// i ServiceDB fungere instansen new ServiceDB ikke når der bliver dannet en constructor i Service
	/*
	public Service(int serviceId, String trim, int duration) {
		this.serviceId = serviceId;
		this.trim = trim;
		this.duration = duration;
	}
	*/

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


	/*
	@Override
	public int hashCode() {
		return Objects.hash(duration, serviceId, trim);
	}
	*/
 // TODO muligvis for komplekst men får vores buildObject til at fungere. i serviceDB
	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if(serviceId != other.serviceId)
			return false;
		if (other.trim == null) {
			if (other.trim != null)
				return false;
		} else if (!trim.equals(other.trim))
			return false;
		if(duration != other.duration)
			return false;
		//return duration == other.duration && serviceId == other.serviceId && Objects.equals(trim, other.trim);
		return true;
	}
*/
}
