package syscThirdYear;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteBuffer;

public class BusCenter implements UDPCommunication{
	
	private static int receivedTotalPassengers;
	private int totalPassengers;
	
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
	public void UDPReceive(int portReceive) {
		DatagramSocket socketR;
		try {
			byte[] receive = new byte[65535];
			socketR = new DatagramSocket(portReceive);
			DatagramPacket packetR = null;
			packetR = new DatagramPacket(receive,receive.length);
			socketR.receive(packetR);
			receive = new byte[65535];
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void main(String[] args) {
		UDPReceive(1678);
	}

}
