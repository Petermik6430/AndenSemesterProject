package db;

import java.util.List;

import model.Service;

public interface ServiceDBIF {

	Service findServiceById(int serviceId)  throws DataAccessException;
	
	public List<Service> findAllService() throws DataAccessException;
	
	public int createService(Service service) throws DataAccessException;  
	
}
