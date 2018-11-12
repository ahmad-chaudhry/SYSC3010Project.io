package 3010Project;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class Bus implements UDPCommunication{
	inkedList<int> receivedTotalPassengers = new LinkedList<int>();
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
		if(receivedTotalPassengers[i]!=0) {
			numberOfPassengersEntering++;
		}
	}
	public void removePassengers(int x) {
		if(receivedTotalPassengers[i]!=0) {
			numberOfPassengersExiting++;
		}
	}
	
	public void UDPSend(InetAddress address, int port) {
		DatagramSocket socket = null ;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			byte buffer[] = null;
				int totalPassengers = totalPassengers(numberOfPassengersEntering, numberOfPassengersExiting);
				buffer = ByteBuffer.allocate(4).putInt(totalPassengers).array();
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,port); 
				if(buffer.length == 0)break;
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
			if(receive.length == 0)break;
			receivedTotalPassengers.add(i,socketR.receive(packetR));
			receive = new byte[65535];
			i++;
	}
	
	public static void main(String[] args) {
		UDPSend(127.0.0.1,1678);
	}
}
