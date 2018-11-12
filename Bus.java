package sysc3010Project;

import java.net.*;

public class Bus implements UDPCommunication{
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
	public int totalPassengers(int numberOfPeopleEntering, int numberOfPeopleExiting) {
		return (numberOfPeopleEntering - numberOfPeopleExiting);
	}
	
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
	        		 int message = totalPassengers(numberOfPassengersEntering, numberOfPassengersExiting);
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
