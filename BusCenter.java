package sysc3010Project;

import java.net.*;

public class BusCenter implements UDPCommunication{
	
	private static int receivedTotalPassengers;
	private int totalPassengers;
	
	public void UDPSend(InetAddress address, int portSend) {
		try {
			int portS = null;
			DatagramSocket socket = null ;
			InetAddress ip = InetAddress.getLocalHost();
			byte buffer[] = null;
			while(true) {
				buf = totalPassengers;
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,port); 
				if(buffer.length == 0)break;
				packet.send(packet);
			}
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
		try {
			byte[] receive = new byte[65535];
			DatagramPacket packetR = null;
			while(true) {
				packetR = new DatagramPacket(receive,receive.length);
				if(receive.length == 0)break;
				receivedTotalPassengers = socketR.receive(packetR);
				receive = new byte[65535];
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
