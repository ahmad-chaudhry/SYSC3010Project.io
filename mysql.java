package sqltestconnection;

import java.sql.*;
import java.util.Scanner;

public class mysql {
	
	private static Connection conn = null;
	private static Scanner scan = new Scanner(System.in);
	
	public static void  main (String[] args)
	{ 
		
		try {
			
			String userName = "root";
			String password = "";
			String url = "jdbc:mysql://localhost:3306/busmonitoringsystemtest?usessl=false";
			conn = DriverManager.getConnection(url,userName,password);
			System.out.println("Enter bus number");
			String busnumber = scan.next();
			System.out.println("Enter total Passengers");
			String totalpassengers = scan.next();
			addData(busnumber,totalpassengers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	private static void addData(String busnumber, String totalpassengers) throws SQLException {
		
		String query = "INSERT INTO details VALUE(null,'"+busnumber+"','"+totalpassengers+"');"; 
		Statement stmt = null;
		stmt = conn.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
		conn.close();
		
	}

}
