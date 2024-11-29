package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Service;

public class ServiceDB implements ServiceDBIF {
	
	private final String FIND_BY_SERVICE_ID = "select * from Service where serviceId = ?";
	
	//private final String FIND_BY_SERVICE_ID = 'select s.serviceId, s.trimType, bd.id, from Service s, BookingDate db' +
	//		"where "; // burde dette være name i steden 
	
	private PreparedStatement ps_findByServiceId;
	
	public ServiceDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
	}

	public Service findServiceById(int serviceId) throws DataAccessException {
		Service res = null;
		try {
			ps_findByServiceId.setInt(1, serviceId);
			ResultSet rs = ps_findByServiceId.executeQuery();
				if(rs.next())
					res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("fejl ved at finde service med id" + serviceId, e);
		}
		return res;
	}


	// TODO der er noget galt med den relationelle model hvorfor skal service direkte tilkopples BookingDate
	
	private Service buildObject(ResultSet rs) throws DataAccessException {
		
		Service service = null;
		try {
			service = new Service();
			service.setServiceId(rs.getInt(1));
			
			
		}catch (SQLException e) {
			throw new DataAccessException("Fejl 101",e);
		}
		return service;
	
	} 
	
	// TODO createService(Service service): void
	// TODO updateService(Service service): void
	// TODO deleteService(int serviceId): void

}
