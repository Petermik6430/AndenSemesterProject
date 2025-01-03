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
	
	public void createService(int serviceId, String name, int duration) throws DataAccessException {
		Service service = new Service(serviceId, name, duration );
	}
	

}
