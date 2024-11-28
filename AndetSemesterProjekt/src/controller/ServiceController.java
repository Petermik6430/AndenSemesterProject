package controller;

import db.ServiceDBIF;

public class ServiceController {

	private ServiceDBIF serviceDB;
	
	public ServiceController(ServiceDBIF serviceDB) {
		this.serviceDB = serviceDB;
	}
	
	// TODO create Service <<create>> serviceType(String )
	// TODO findService findService(int serviceId): Service
	// TODO updateService updateService(Service service): void
	// TODO deleteService deleteService(int serviceId): void
}
