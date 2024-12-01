package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Service;

public class ServiceDB implements ServiceDBIF {
	
	private final String FIND_BY_SERVICE_ID = "select * from serviceId, name where serviceId = ?";
	private final String FIND_ALL_SERVICE = "select * from Service";
	
	//private final String FIND_BY_SERVICE_ID = 'select s.serviceId, s.trimType, bd.id, from Service s, BookingDate db' +
	//		"where "; // burde dette være name i steden 
	
	private PreparedStatement ps_findByServiceId;
	private PreparedStatement ps_findAllService;
	
	public ServiceDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
	try {
		ps_findByServiceId = con.prepareStatement(FIND_BY_SERVICE_ID);
		ps_findAllService = con.prepareStatement(FIND_ALL_SERVICE);
		
		}catch (SQLException e) {
			throw new DataAccessException( "fejl ved oprettelse til database", e);
		}
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
			service.setName(rs.getString(2));
			
			
		}catch (SQLException e) {
			throw new DataAccessException("Fejl 101",e);
		}
		return service;
	
	} 
	
	public List<Service> findAllService() throws DataAccessException {
		List<Service> res = null;
		try {
			ResultSet rs = ps_findAllService.executeQuery();
			res = buildObject1(rs);
		} catch(SQLException e) {
			throw new DataAccessException("Fejl",e);
		}
		return null;
		
		
	}
	
	private List<Service> buildObject1(ResultSet rs) throws DataAccessException {
		List<Service> res = new ArrayList<>();
			try {
				while (rs.next()) {
					Service ser = buildObject(rs);
					res.add(ser);
				}
					} catch(SQLException e) {
					throw new DataAccessException("fejl",e);
				}
		return res;
	}
	
	// TODO createService(Service service): void
	// TODO updateService(Service service): void
	// TODO deleteService(int serviceId): void

}
