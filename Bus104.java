package syscThirdYear;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Bus104{
	//ID or Bus number
	private Integer ID;
		
	//global variable to keep track of people entering the bus
	private int numberOfPassengersEntering;
	
	//global variable to keep track of people exiting the bus
	private int numberOfPassengersExiting;
		
	//Bus capacity of this bus (green,yellow,or red) refer to busCapacityZone() method for zone ranges
	private String busCapacity;
			
	//initializer for the bus
	public Bus104(Integer ID) {
		this.ID = 104;
		// TODO Auto-generated constructor stub
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
		if(this.totalPassengers()>=4 && this.totalPassengers()<=7) {
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
			String sendStr = new String(this.ID.toString()+ "," +this.getBusCapacityZone()+",");
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
		InetAddress addr = InetAddress.getByName("10.0.0.12");
		int portSend = 120;
		int busNum = 104;
		Bus testBus = new Bus(busNum);
		
		//serial port code done by partner just for hardware (arduino) 
		SerialPort serialPort = new SerialPort("/dev/ttyACM0");

        while (true) {
           try {
                serialPort.openPort();//Open serial port
                serialPort.setParams(9600, 8, 1, 0);//Set params.
                String s = serialPort.readString(1);
                
                if(s.equals("1")) {
                	testBus.addPassenger();
                	testBus.updateBusCapacityZone();
                    testBus.UDPSend(testBus,addr, portSend);
                }if(s.equals("2")){
                	testBus.removePassenger();
                	testBus.updateBusCapacityZone();
                    testBus.UDPSend(testBus,addr, portSend);
                }if(s.equals("3")) {
                	System.out.println("Person entered and Person exited");
                	testBus.updateBusCapacityZone();
                    testBus.UDPSend(testBus,addr, portSend);
                }if(s.equals("4")) {
                	System.out.println("Sensor 1 Trigger Error");
                }else if(s.equals("5")) {
                	System.out.println("Sensor 2 Trigger Error");
                }else if(s.equals("6")) {
                	System.out.println("Sensor 3 Trigger Error");
                }
                
                System.out.println(s);
                serialPort.closePort();//Close serial port
            }
           catch (SerialPortException ex) {
                System.out.println("Error");
            }
        }
	}

}
