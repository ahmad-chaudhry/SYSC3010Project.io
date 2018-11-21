package syscThirdYear;

import java.net.*;

public class Bus{
	//ID or Bus number
	private Integer ID;
	
	//global variable to keep track of people entering the bus
	private int numberOfPassengersEntering;
	
	//global variable to keep track of people exiting the bus
	private int numberOfPassengersExiting;
	
	//Bus capacity of this bus (green,yellow,or red) refer to busCapacityZone() method for zone ranges
	private String busCapacity;
		
	//initializer for the bus
	public Bus(Integer ID) {
		this.numberOfPassengersEntering = 0;
		this.numberOfPassengersExiting = 0;
		//busCapacityZone(this); //sets the bus capacity based on people on the bus at the start
		this.ID = ID;
		this.updateBusCapacityZone();
	}
	
	public Integer getID() {
		return this.ID;
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
	public Integer totalPassengers() {
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
	
	//returns the Bus Capacity zone
	public String getBusCapacityZone() {
		return busCapacity;
	}
	
	public void updateBusCapacityZone() {
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
	public void UDPSend(Bus testBus,InetAddress address, int port) {
		DatagramSocket datagramSocket = null ;
		try {
			datagramSocket = new DatagramSocket();
			this.updateBusCapacityZone();
			String sendStr = new String(this.ID.toString()+ "," +this.getBusCapacityZone());
			byte[] buffer = sendStr.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
	        datagramSocket.send(packet);
		}
		catch( Exception e ){
	         System.out.println( e );
	      }
	      finally{
	         if( datagramSocket != null )datagramSocket.close();
	      }
		
	}
	public static void main(String[] args) throws Exception{
		InetAddress addr = InetAddress.getByName("169.254.164.174");
		int portSend = 120;
		Bus testBus = new Bus(99);
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.UDPSend(testBus,addr, portSend);
	}
}