package db;

import controller.DataAccessException;
import model.Service;

public interface ServiceDBIF {

	Service findServiceById(int serviceId)  throws DataAccessException;
	
		// TODO createService(Service service): void
		// TODO updateService(Service service): void
		// TODO deleteService(int serviceId): void
}
