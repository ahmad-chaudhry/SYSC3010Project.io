package syscThirdYear;

import java.util.*;
import java.net.*;

public class BusCenter{
	
	//Final string received
	private static String receivedStr;
	List<String> dbUpdate;
	
	//Variables to send to the Database
	private String finalBusID;
	private String finalBusZone;
	
	//Array List to keep track of all busses
	private static ArrayList<Bus> allBusses = new ArrayList<Bus>();
	
	//Pretend database until partner finished Database
	private ArrayList<String> dataBase = new ArrayList<String>();
		
	//getter for ArrayList
	public void addBusToList(Bus x){
		allBusses.add(x);
	}
	//get list of busses
	public ArrayList<Bus> getBusList() {
		return allBusses;
	}
	

	//get pretend database
	public ArrayList<String> getDB() {
		return dataBase;
	}
	
	//Receive values (of traffic on Bus) from the bus and store it 
	public void UDPReceive(int portReceive) {
		try {
			DatagramSocket socketR = new DatagramSocket(portReceive);
			byte[] buffer = new byte[256];
			DatagramPacket packetR = new DatagramPacket(buffer,buffer.length);
			socketR.receive(packetR);
			System.out.println(new String(packetR.getData()).trim() ) ;
			receivedStr = new String(packetR.getData());
			dbUpdate = Arrays.asList(receivedStr.split(","));
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//returns string being received through UDP receive
	public String getReceivedString() {
		return receivedStr;
	}
	
	//Sends an ACK message back to the 
	@SuppressWarnings("resource")
	public void testUDPReceive(int portReceive) {
		try {
			DatagramSocket socketR = new DatagramSocket(portReceive);
			byte[] buffer = new byte[256];
			DatagramPacket packetR = new DatagramPacket(buffer,buffer.length);
			socketR.receive(packetR);
			System.out.println(new String(packetR.getData()).trim() ) ;
			InetAddress addressSendBack = packetR.getAddress();
			int portSendBack = packetR.getPort();
			packetR = new DatagramPacket(buffer, buffer.length,addressSendBack,portSendBack);
			socketR.send(packetR);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//Update global variables used to update the database
	public void updateReceivedVariables() {
		finalBusID = dbUpdate.get(0);
		finalBusZone = dbUpdate.get(1);
	}
	
	//Add value to the Database that has been received from the BUS
	public void updateDatabase() {
		dataBase.add(finalBusID);
		dataBase.add(finalBusZone);
	}
	
	public static void main(String[] args) {
		BusCenter testCenter = new BusCenter();
		int portReceive = 120;
		testCenter.UDPReceive(portReceive);
	}

}
