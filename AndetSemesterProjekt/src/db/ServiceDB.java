package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Service;

public class ServiceDB implements ServiceDBIF {
	
	private DBConnection dbc;
	
	private final String FIND_BY_SERVICE_ID = "select * from Service where id = ?";
	private final String FIND_ALL_SERVICE = "select * from Service";
	private final String CREATE_SERVICE = "insert into service(serviceId, name, duration) values (?,?,?)";
	
	
	private PreparedStatement ps_findByServiceId;
	private PreparedStatement ps_findAllService;
	private PreparedStatement ps_createService;
	

	
	public ServiceDB() throws DataAccessException {
		init();
		}

	private void init() throws DataAccessException {
	try {
		dbc = DBConnection.getInstance();
		Connection con = DBConnection.getInstance().getConnection();
		
		ps_findByServiceId = con.prepareStatement(FIND_BY_SERVICE_ID);
		ps_findAllService = con.prepareStatement(FIND_ALL_SERVICE);
		ps_createService = con.prepareStatement(CREATE_SERVICE, Statement.RETURN_GENERATED_KEYS);
		
		}catch (SQLException e) {
			throw new DataAccessException( "fejl ved oprettelse til database", e);
		}
	}

	public Service findServiceById(int id) throws DataAccessException {
		Service res = null;
		try {
			ps_findByServiceId.setInt(1, id);
			ResultSet rs = ps_findByServiceId.executeQuery();
				if(rs.next()) 
				res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("fejl ved at finde service med id" + id, e);
		}
		return res;
	}


	
	private Service buildObject(ResultSet rs) throws DataAccessException {
		
		Service service = null;
		try {
			service = new Service();
			service.setServiceId(rs.getInt(1));
			service.setName(rs.getString(2));
			service.setDuration(rs.getInt(3));
			
			
		}catch (SQLException e) {
			throw new DataAccessException("Fejl 101",e);
		}
		return service;
	
	} 
	
	
	public List<Service> findAllService() throws DataAccessException {
		List<Service> res = null;
		try {
			ResultSet rs = ps_findAllService.executeQuery();
			res = buildObjects(rs);
		} catch(SQLException e) {
			throw new DataAccessException("Fejl",e);
		}
		return res;
		
		
	}
	
	private List<Service> buildObjects(ResultSet rs) throws DataAccessException {
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
	

	@Override
    public int createService(Service service) throws DataAccessException {
        int serviceId = -1;
        dbc.startTransaction();
        try {
        ps_createService.setString(2, service.getName());
        ps_createService.setInt(3, service.getDuration());
        ps_createService.executeUpdate();

        ResultSet generatedKeys = ps_createService.getGeneratedKeys();
        if(generatedKeys.next()) serviceId = generatedKeys.getInt(1);
        dbc.commitTransaction();
        } catch (SQLException e) {
        	dbc.rollbackTransaction();
            throw new DataAccessException("", e); // TODO lav en beskrivende fejl besked.
        }
    

        return serviceId;
    }

}
