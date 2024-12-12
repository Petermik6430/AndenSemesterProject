package controller;

import java.util.List;

import db.DataAccessException;
import db.ServiceDB;
import db.ServiceDBIF;
import model.Service;

public class ServiceController {

	private ServiceDBIF serviceDB;
	
	public ServiceController() throws DataAccessException {
		serviceDB = new ServiceDB();
	}
	
	public Service findServiceById(int serviceId) throws DataAccessException {
		Service ser = serviceDB.findServiceById(serviceId);
		return ser;
	}
	
	public List<Service> findAllService() throws DataAccessException {
		return serviceDB.findAllService();
	}
	
	public Service createService(int serviceId, String name, int duration) throws DataAccessException {
		Service service = new Service(serviceId, name, duration );
		
		return service;
	}
	
//	public Service findServiceByName(String serviceName) throws DataAccessException {
//		Service ser = serviceDB.findServiceByName(serviceName);
//		return ser;
		
//	}
	// TODO updateService updateService(Service service):  void
	// TODO deleteService deleteService(int serviceId): void
}
