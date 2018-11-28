package syscThirdYear;

import java.util.*;
import java.net.*;
import java.sql.*;

public class BusCenter{
	
	/**
	 * Global variables for Database Updating
	 */
	static Connection conn = null;
	static ArrayList<String> columns;
	static ArrayList<String> colvalues;
	//Variables to send to the Database
	private String finalBusID = "1";
	private String finalBusZone = "green";
	
	/**
	 * 
	 * GLOBAL variables needed for the Receiving of data
	 * 
	 */
	//Final string received from UDPReceive
	private static String receivedStr;
	
	//List containing the two received values [0]=BusID, [1]=Zone
	List<String> dbUpdate;
	
	//Array List to keep track of all busses
	private static ArrayList<Bus> allBusses = new ArrayList<Bus>();
	
	//Add a Bus to the ArrayList of Busses
	public void addBusToList(Bus x){
		allBusses.add(x);
	}
	//get list of busses
	public ArrayList<Bus> getBusList() {
		return allBusses;
	}
	
	//Receive values (of traffic on Bus) from the bus and store it 
	public void UDPReceive(int portReceive) {
		try {
			DatagramSocket socketR = new DatagramSocket(portReceive);
			for(;;) {
				byte[] buffer = new byte[256];
				DatagramPacket packetR = new DatagramPacket(buffer,buffer.length);
				socketR.receive(packetR);
				System.out.println(new String(packetR.getData()).trim() ) ;
				receivedStr = new String(packetR.getData());
				dbUpdate = Arrays.asList(receivedStr.split(","));
				updateReceivedVariables();
				//for(int i=0; i<allBusses.size();i++) {
					//if(allBusses.get(i).getID().toString().equals(finalBusID)) {
				updateDatabase();
					//}
				//}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//returns string being received through UDP receive
	public String getReceivedString() {
		return receivedStr;
	}
	
	//Update global variables used to update the database
	public void updateReceivedVariables() {
		finalBusID = dbUpdate.get(0);
		finalBusZone = dbUpdate.get(1);
	}
	public String getfinalID() {
		return this.finalBusID;
	}
	public String getfinalBusZone() {
		return this.finalBusZone;
	}
	
	public void createDatabase() throws SQLException{
		
	}
	//Add value to the Database that has been received from the BUS
	public void updateDatabase() throws SQLException {
		

			String userName = "admin";
			String password = "password";
			String url = "jdbc:mysql://localhost:3306/busmonitoringsystem?usessl=false";
				
			try {
				conn = DriverManager.getConnection(url,userName,password); 
				columns = new ArrayList<String>();
					
				DatabaseMetaData md = conn.getMetaData();
				ResultSet rs = md.getColumns(null, null, "details1", "%");
				while(rs.next())
				{
					columns.add(rs.getString(4));
				}
					
					
				Statement stmt = conn.createStatement();
				for (int i = 0; i<columns.size(); i++)
				{
					String query = "UPDATE details1 SET capacityzones =" + finalBusZone + "where busnumber =" + finalBusID; 
					stmt.executeUpdate(query);
				}
					
				stmt.close();
				conn.close();
				} catch (SQLException e) 
				
				{
					e.printStackTrace();
				}
	}
	
	public static void main(String[] args) throws SQLException {
		BusCenter testCenter = new BusCenter();
		int portReceive = 120;
		Bus testBus1 = new Bus(99);
		allBusses.add(testBus1);
		//testCenter.UDPReceive(portReceive);
		testCenter.updateDatabase();
	}

}
