package syscThirdYear;

import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class BusCenter implements UDPCommunication{
	
	//Pretend database until partner finished Database
	private BusDatabase db = new BusDatabase();
	//global variable to keep track of the total passengers
	private static int receivedTotalPassengers;
	private int totalPassengers;
	//Array List to keep track of all busses
	private static ArrayList<Bus> allBusses = new ArrayList<Bus>();
	//getter for ArrayList
	public ArrayList<Bus> getList(){
		return allBusses;
	}
	public void addBusToList(Bus x) {
		allBusses.add(x);
	}
	//
	//Send value from bus center
	public void UDPSend(InetAddress address, int portSend) {
		DatagramSocket socket = null ;
		try {
			int portS;
			InetAddress ip = InetAddress.getLocalHost();
			byte buffer[] = null;
			buffer = ByteBuffer.allocate(4).putInt(totalPassengers).array();
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,portSend); 
			socket.send(packet);
		}
		catch( Exception e ){
	         System.out.println( e ) ;
	      }
	      finally{
	         if( socket != null )socket.close() ;
	      }
	}
	
	//Receive values (of traffic on Bus) from the bus and store it 
	public void UDPReceive(int portReceive) {
		DatagramSocket socketR;
		try {
			byte[] receive = new byte[65535];
			socketR = new DatagramSocket(portReceive);
			DatagramPacket packetR = null;
			packetR = new DatagramPacket(receive,receive.length);
			socketR.receive(packetR);
			receive = new byte[65535];
			//return 
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//Add value to the Database that has been received from the BUS
	public void updateDatabase(int x) {
		db.addToDB(x);
	}
	
	public void main(String[] args) {
		UDPReceive(1678);
	}

}
