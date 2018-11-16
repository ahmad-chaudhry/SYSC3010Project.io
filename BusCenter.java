package syscThirdYear;

import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.*;
public class BusCenter implements UDPCommunication{
	
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
	
	//Send value from bus center
	public void UDPSend(InetAddress address, int portSend) {
		DatagramSocket socket = null ;
		try {
			int portS;
			byte buffer[] = null;
			buffer = ByteBuffer.allocate(4).putInt(totalPassengers).array();
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,portSend); 
			socket.send(packet);
		}
		catch( Exception e ){
	         System.out.println( e );
	      }
	      finally{
	         if( socket != null )socket.close() ;
	      }
	}
	
	//Receive values (of traffic on Bus) from the bus and store it 
	public void UDPReceive(int portReceive) {
		DatagramSocket socketR;
		try {
			socketR = new DatagramSocket(portReceive);
			byte[] buffer = new byte[256];
			DatagramPacket packetR = new DatagramPacket(buffer,buffer.length);
			socketR.receive(packetR);
			InetAddress addressSendBack = packetR.getAddress();
			int portSendBack = packetR.getPort();
			packetR = new DatagramPacket(buffer, buffer.length,addressSendBack,portSendBack);
			socketR.send(packetR);
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
		int portReceive = 1678;
		testCenter.UDPReceive(portReceive);
	}

}
