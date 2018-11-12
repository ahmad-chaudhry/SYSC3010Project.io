package sysc3010Project;

import java.net.*;

public class BusCenter implements UDPCommunication{
	
	private int totalPassengers;
	
	public void UDPSend(Packet x) {
		DatagramSocket socket = null ;
		try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( "10.0.0.12" ) ;
	         int port = 132;
	         socket = new DatagramSocket() ;
	         
	         while (true)
	         {
	        		 String message = totalPassengers(numberOfPassengersEntering, numberOfPassengersExiting);
	        		 if (message.length()==0) break;
	        		 byte [] data = message.getBytes();
	        		 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        		 socket.send( packet );
	         } 
	      }
		catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
	      }
	}
	public void UDPReceive(Packet x) {
		
	}
}
