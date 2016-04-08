/**
 * 
 */
package baseClasses;

import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kaikun
 *
 *	Our own connection class which is already a set up connection and just needs to be created to directly 
 *	fetch sqlQueries to the database server. Possibly the DB-Name/port/localhost must be adjusted.
 *
 */
public class ConnectionClass {
	
	/* localhost, port and DB-Name may have to be adjusted */
	private static final String connection_url = "jdbc:mysql://localhost:3306/webshopDB";
	/* Connection data, may has to be changed to yours */
	private String username = "root";
	private String password = "";
	private Connection connectionDB;
	
	/**
	 * Constructor method creates a connection to the database.
	 */
	public ConnectionClass(){
		Connection();
	}

	
	/**
	 * 
	 * @param sqlQuery 
	 * @return ResultSet, which represents the given rows from the DB.
	 */
	public ResultSet fetch(String sqlQuery){
		PreparedStatement stat;
		
		try {
			stat = connectionDB.prepareStatement(sqlQuery);
			stat.execute();
			return stat.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	/**
	 * Sets up a connection to the DB defined by the connection_url with the user / password data.
	 */
	private void Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties user = new Properties();
			user.put("user", username);
			user.put("password", password);
			connectionDB = DriverManager.getConnection(connection_url,user);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
		


	

}
