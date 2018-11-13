package syscThirdYear;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class Bus implements UDPCommunication{
	LinkedList<Integer> receivedTotalPassengers = new LinkedList<>();
	int hold;
	private static int i=0;
	private int numberOfPassengersEntering;
	private int numberOfPassengersExiting;
	
	public Bus(int numberOfPassengersEntering, int numberOfPassengersExiting) {
		this.numberOfPassengersEntering = numberOfPassengersEntering;
		this.numberOfPassengersExiting = numberOfPassengersExiting;
	}
	public int getNumberOfPassengersEntering() {
		return numberOfPassengersEntering;
	}
	public int getNumberOfPassengersExiting() {
		return numberOfPassengersExiting;
	}
	public int totalPassengers() {
		return (numberOfPassengersEntering - numberOfPassengersExiting);
	}
	public void addPassengers() {
		numberOfPassengersEntering++;
	}
	public void removePassengers() {
		numberOfPassengersExiting++;
	}
	
	//send total passenger number to the bus center
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
	         System.out.println( e ) ;
	      }
	      finally{
	         if( socket != null )socket.close() ;
	      }
	}
	
	//Receive values 
	public void UDPReceive(int portReceive){
		DatagramSocket socketR=null;
		try {
			socketR = new DatagramSocket(portReceive);
			byte[] receive = new byte[65535];
			DatagramPacket packetR = null;
			packetR = new DatagramPacket(receive,receive.length);
			socketR.receive(packetR);
			receive = new byte[65535];
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void main(String[] args) throws UnknownHostException{
		InetAddress ip = InetAddress.getLocalHost();
		Bus testBus = new Bus(5,4);
		UDPSend(ip,1678);
		System.out.println("null");
	}
}