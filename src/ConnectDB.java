import java.sql.DriverManager;

import java.sql.*;


public class ConnectDB {
     public static Connection connect()
     {
    	 try {
    		 Class.forName("com.mysql.jdbc.Driver");
        	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/stud","root","root");
        	 return con;	 
    	 }catch(Exception e){
    		 System.out.println(e);
    	 }
    	 return null;
    	
     }
     
     public static void main() {
    	 
     }
}
