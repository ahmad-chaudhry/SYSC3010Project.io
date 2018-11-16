package syscThirdYear;

import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.*;


public class Bus implements UDPCommunication{
	private static int i=0;
	
	//global variable to keep track of people entering the bus
	private int numberOfPassengersEntering;
	
	//global variable to keep track of people exiting the bus
	private int numberOfPassengersExiting;
	
	//Bus capacity of this bus (green,yellow,or red) refer to busCapacityZone() method for zone ranges
		private String busCapacity;
		
	//initializer for the bus
	public Bus() {
		this.numberOfPassengersEntering = 0;
		this.numberOfPassengersExiting = 0;
		busCapacityZone(this); //sets the bus capacity based on people on the bus at the start
	}
	
	//getter for number of people entering
	public int getNumberOfPassengersEntering() {
		return numberOfPassengersEntering;
	}
	
	//getter for number of people exiting
	public int getNumberOfPassengersExiting() {
		return numberOfPassengersExiting;
	}
	
	//returns the total number of people on the bus
	public int totalPassengers() {
		return (numberOfPassengersEntering - numberOfPassengersExiting);
	}
	
	//adds a passenger to the bus (interface with hardware code to increment any time somebody enters the bus)
	public void addPassenger() {
		numberOfPassengersEntering++;
	}
	
	//removes a passenger to the bus (interface with hardware code to decrement any time somebody gets off the bus)
	public void removePassenger() {
		numberOfPassengersExiting++;
	}
	
	public String getBusCapacityZone() {
		return busCapacity;
	}
	public void busCapacityZone(Bus bus) {
		//Bus Capacity == 10
		//Green Zone = <=3 passengers
		//Yellow Zone >4 && <=7
		//Red Zone >7 && <=10
		if(this.totalPassengers()>=0 && this.totalPassengers()<=3) {
			busCapacity = "green";
		}
		if(this.totalPassengers()>=3 && this.totalPassengers()<=7) {
			busCapacity = "yellow";
		}
		if(this.totalPassengers()>=8 && this.totalPassengers()<=10) {
			busCapacity = "red";
		}
	}
	//send total passenger number to the bus center
	@SuppressWarnings("null")
	public void UDPSend(InetAddress address, int port) {
		DatagramSocket socket = null ;
		try {
			byte buffer[];
			int totalPassengers = totalPassengers();
			buffer = ByteBuffer.allocate(4).putInt(totalPassengers).array();
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,port); 
			socket.send(packet);
		}
		catch( Exception e ){
	         System.out.println( e );
	      }
	      finally{
	         if( socket != null )socket.close();
	      }
		
	}
	
	//Receive values 
	public void UDPReceive(int portReceive){
		DatagramSocket socketR=null;
		try {
			socketR = new DatagramSocket(portReceive);
			byte[] buffer = new byte[256];
			DatagramPacket packetR = new DatagramPacket(buffer,buffer.length);
			socketR.receive(packetR);
			InetAddress addressSendBack = packetR.getAddress();
			int portSendBack = packetR.getPort();
			packetR = new DatagramPacket(buffer, buffer.length,addressSendBack,portSendBack);
			socketR.send(packetR);
			//receive = new byte[65535];
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws UnknownHostException{
		//byte[] ipAddr = new byte[]{172, 17, 32, 1};
		byte[] ipTest = new byte[] {10,0,0,1};
		InetAddress testAddrSend = InetAddress.getByAddress(ipTest);
		//InetAddress localhost = InetAddress.getLocalHost();
		//InetAddress addrSend = InetAddress.getByAddress(ipAddr);
		int portSend = 1678;
		Bus testBus = new Bus();
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.UDPSend(testAddrSend, portSend);
	}
}