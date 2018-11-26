package syscThirdYear;

import java.util.*;
import java.net.*;
import java.sql.*;

public class BusCenter{
	
	/**
	 * GLOBAL variables needed for the Database
	 */
	static Connection conn = null;
	static Scanner scan = new Scanner(System.in);
	static ArrayList<String> columns;
	static ArrayList<String> colvalues;
	//Variables to send to the Database
	private String finalBusID;
	private String finalBusZone;
	
	/**
	 * 
	 * GLOBAL variables needed for the Receiving of data
	 * 
	 */
	//Total number of busses associated with the BusCenter
	private static int totalBusses;
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
				for(int i=0; i<totalBusses;i++) {
					if(allBusses.get(i).getID().toString().equals(finalBusID)) {
						updateDatabase();
					}
				}
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
	
	//Add value to the Database that has been received from the BUS
	public void updateDatabase() {
		//Database work done by other group member
		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/buscapacitymonitoring?usessl=false";
		try {
			conn = DriverManager.getConnection(url,userName,password);
			String busnumber = finalBusID;
			columns = new ArrayList<String>();
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(null, null, "details", "%");
			while(rs.next())
			{
				columns.add(rs.getString(4));
			}
			colvalues = new ArrayList<String>();
			for (int i = 0; i< columns.size();i++)
			{
				System.out.println(columns.get(i) + ": ");
				String change = finalBusZone;
				
				colvalues.add(change);
			}
			
			Statement stmt = conn.createStatement();
			for (int i = 0; i<columns.size(); i++)
			{
				String query = "UPDATE details SET " + columns.get(i) + " = '" + colvalues.get(i)      
				+ "' WHERE " + "  busnumber = " + busnumber;
				stmt.executeUpdate(query);
			}
			
			stmt.close();
			conn.close();
		}catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		BusCenter testCenter = new BusCenter();
		int portReceive = 120;
		Bus testBus1 = new Bus(1);
		allBusses.add(testBus1);
		testCenter.updateReceivedVariables();
		testCenter.UDPReceive(portReceive);
	}

}
