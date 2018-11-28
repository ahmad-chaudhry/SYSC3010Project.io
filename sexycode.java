package sqltestconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class updatedata {
	
	static Connection conn = null;
	static ArrayList<String> columns;
	static ArrayList<String> colvalues;

	
	public static void  main (String[] args)
	{ 
		
		
			
			String userName = "root";
			String password = "";
			String url = "jdbc:mysql://localhost:3306/busmonitoringsystemtest?usessl=false";
			
			try {
				
				
				conn = DriverManager.getConnection(url,userName,password); 
				

				columns = new ArrayList<String>();
				
				DatabaseMetaData md = conn.getMetaData();
				ResultSet rs = md.getColumns(null, null, "details3", "%");
				while(rs.next())
				{
					columns.add(rs.getString(4));
				}
				
				
				Statement stmt = conn.createStatement();
				for (int i = 0; i<columns.size(); i++)
				{
					String query = "UPDATE details3 SET capacityzones = '2' where busnumber = 4"; 
					stmt.executeUpdate(query);
				}
				
				stmt.close();
				conn.close();
				
				
				
				
			} catch (SQLException e) 
			
			{
				e.printStackTrace();
			}
			
		
		
	}
}

