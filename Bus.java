package sysc3010Project;

import java.net.*;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class Bus implements UDPCommunication{
	LinkedList<Byte> receivedTotalPassengers = new LinkedList<Byte>();
	private static int i=0;
	private int numberOfPassengersEntering = 5;
	private int numberOfPassengersExiting = 1;
	
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
	public int totalPassengers(int numberOfPeopleEntering, int numberOfPeopleExiting) {
		return (numberOfPeopleEntering - numberOfPeopleExiting);
	}
	public void addPassengers(int x) {
		if(receivedTotalPassengers.get(i)!=0) {
			numberOfPassengersEntering++;
		}
	}
	public void removePassengers(int x) {
		if(receivedTotalPassengers.get(i)!=0) {
			numberOfPassengersExiting++;
		}
	}
	
	@SuppressWarnings("null")
	public void UDPSend(InetAddress address, int port) {
		DatagramSocket socket = null ;
		try {
			byte buffer[];
			
				int totalPassengers = totalPassengers(numberOfPassengersEntering, numberOfPassengersExiting);
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
	public void UDPReceive(int portReceive) {
		DatagramSocket socketR = new DatagramSocket(portReceive);
		byte[] receive = new byte[65535];
		DatagramPacket packetR = null;
			packetR = new DatagramPacket(receive,receive.length);
			receivedTotalPassengers.addAll(socketR.receive(packetR));
			receive = new byte[65535];
			i++;
	}
	
	public static void main(String[] args) {
		UDPSend(127.0.0.1,1678);
	}
	@Override
	public void UDPSend(Packet x) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void UDPReceive(Packet x) {
		// TODO Auto-generated method stub
		
	}
}