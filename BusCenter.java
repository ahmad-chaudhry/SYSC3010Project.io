package syscThirdYear;

import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.*;
public class BusCenter{
	
	//Pretend database until partner finished Database
	private ArrayList<Integer> dataBase = new ArrayList<Integer>();
	
	//global variable to keep track of the total passengers
	private int totalPassengers;
	
	//get pretend database
	public ArrayList<Integer> getDB() {
		return dataBase;
	}
	
	//Array List to keep track of all busses
	private static ArrayList<Bus> allBusses = new ArrayList<Bus>();
	
	//getter for ArrayList
	public ArrayList<Bus> getList(){
		return allBusses;
	}
	//Add new bus to our list of busses
	public void addBusToList(Bus x) {
		allBusses.add(x);
	}
	//Receive values (of traffic on Bus) from the bus and store it 
	public void UDPReceive(int portReceive) {
		try {
			DatagramSocket socketR = new DatagramSocket(portReceive);
			byte[] buffer = new byte[256];
			DatagramPacket packetR = new DatagramPacket(buffer,buffer.length);
			socketR.receive(packetR);
			System.out.println(new String(packetR.getData()).trim() ) ;
			//InetAddress addressSendBack = packetR.getAddress();
			//int portSendBack = packetR.getPort();
			//packetR = new DatagramPacket(buffer, buffer.length,addressSendBack,portSendBack);
			//socketR.send(packetR);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//Add value to the Database that has been received from the BUS
	public void updateDatabase(int x) {
		dataBase.add(x);
	}
	
	public static void main(String[] args) {
		BusCenter testCenter = new BusCenter();
		int portReceive = 120;
		testCenter.UDPReceive(portReceive);
	}

}
