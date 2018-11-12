package sysc3010Project;

public class Packet {
	
	private int totalPassengers;
	private int numberOfPassengersEntering;
	private int numberOfPassengersExiting;
	private int[] packetArr = new int[3];
	
	public Packet(int totalPassengers, int numberOfPassengersEntering, int numberOfPassengersExiting) {
		this.numberOfPassengersEntering = numberOfPassengersEntering;
		this.numberOfPassengersExiting = numberOfPassengersExiting;
		this.totalPassengers = totalPassengers; 
		packetArr[0] = totalPassengers;
		packetArr[1] = numberOfPassengersEntering;
		packetArr[2] = numberOfPassengersExiting;
	}
	public String getPacketArr(int n) {
		if(n==1) {
			return "packetArr[0]";
		}else if(n==2) {
			return (packetArr[1] + " " + packetArr[2]);
		}
		return "";
	}

}
