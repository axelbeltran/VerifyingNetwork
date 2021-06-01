/**
 * 
 */
package verifyingNetwork;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Axel Ignacio Beltran Alvarez
 * 30/05/2021
 * License: GNU GENERAL PUBLIC LICENSE
 * 
 */


public class conSQL {
	
	private Connection objCon() throws SQLException {
		Connection con=DriverManager.getConnection("jdbc:mysql://192.168.1.140:3306/ip","root","");
    	return con;
	}
	
	public boolean validateUser(String userId, String pass) {
		boolean v, result = false;
		try{
			Statement stmt = objCon().createStatement();  
	    	ResultSet rs = stmt.executeQuery("SELECT pass FROM users WHERE userId = " + userId);
	    	rs.next();
	    	v = BCrypt.checkpw(pass, rs.getString("pass"));
	    	if(v) {
	    		result = true;
	    	}
	    	else {
	    		result = false;
	    	}
	    	objCon().close();  
    	}
    	catch(Exception e){
		} 
		return result;
	}
	
	public void getIp() {
		try {
			Statement stmt=objCon().createStatement();  
	    	ResultSet rs=stmt.executeQuery("select * from ips");  
	    	while(rs.next()) {
	    		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
	    		
	    		InetAddress ping;
				ping = InetAddress.getByName(rs.getString(2));
				if(ping.isReachable(1000)){ // tiempo de espera en milisegundos
					System.out.println(rs.getString(2)+" - responde!");
				}else {
					System.err.println(rs.getString(2)+" - no responde!");
				}
	    	}
	    	objCon().close();  
		}
		catch(Exception e){
			System.out.println(e);
		} 
	}

}
