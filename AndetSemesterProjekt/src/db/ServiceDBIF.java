package db;

import java.util.List;

import model.Service;

public interface ServiceDBIF {

	Service findServiceById(int serviceId)  throws DataAccessException;
	
	public List<Service> findAllService() throws DataAccessException;
	
	
	
	
	
		// TODO createService(Service service): void
	public void createService(Service service);
	
		// TODO updateService(Service service): void
		// TODO deleteService(int serviceId): void
}
