package model;

import java.util.Objects;

public class Service {

	private int serviceId;
	private String trim;
	private int duration;
	
	// i ServiceDB fungere instansen new ServiceDB ikke når der bliver dannet en constructor i Service
	/*
	public Service(int serviceId, String trim, int duration) {
		this.serviceId = serviceId;
		this.trim = trim;
		this.duration = duration;
	}
	*/

	public Service() {
	}


	public int getServiceId() {
		return serviceId;
	}
	public String getTrim() {
		return trim;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public void setTrim(String trim) {
		this.trim = trim;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

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
