package db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import db.DBConnection;

public class DBConnectionTest {

	
	@Test
	public void testGetConnection() {

		try {
			Connection c = DBConnection.getInstance().getConnection();
			assertNotNull(c);
		} catch (Exception e) {
			fail("issues with dbconnection");
		}
	}

}
