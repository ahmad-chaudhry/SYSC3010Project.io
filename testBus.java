package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.Timer;

import org.junit.jupiter.api.Test;
import junit.framework.Assert;
import junit.framework.TestCase;
import syscThirdYear.Bus;
import syscThirdYear.BusCenter;

class testBus extends TestCase{
	
	BusCenter testBC = new BusCenter();
	private InetAddress address;
	private int port;
	
	@Test 
	void testUDPSend(){
		Bus testBus = new Bus();
		testBus.addPassenger();
		testBus.UDPSend(testBus, address, port);
		//this should send the total number of passengers to a chosen IP address on a certain port
	}
	
	@Test
	void testUDPReceive() {
		Bus testBus = new Bus();
		testBus.UDPSend(testBus, address, port);
		testBC.UDPReceive(port);
	}
	
	@Test
	void testBusCapacityZone() {
		Bus testBus = new Bus();
		testBus.addPassenger();
		String testZone = testBus.getBusCapacityZone();
		Assert.assertEquals(testZone, "green");
	}
	@Test
	void testUDPSpeed() {
		int testCount = 0;
		Bus testBus = new Bus();
		long startTime = System.currentTimeMillis();
		testBus.UDPSend(testBus,address, port);
		long elapsedTime = System.currentTimeMillis() - startTime;
		long elapsedSeconds = elapsedTime / 1000;
		long secondsDisplay = elapsedSeconds % 60;
		long elapsedMinutes = elapsedSeconds / 60;
	}
	@Test
	void testAddPassenger(int x) {
		Bus testBus = new Bus();
		for(int i=0;i<x;i++) {
			testBus.addPassenger();
		}
		int finalans = testBus.totalPassengers();
		Assert.assertEquals(finalans, x);  
	}
	@Test
	void testRemovePassenger(int totalPassengers,int remove) {
		Bus testBus = new Bus();
		for(int i=0;i<totalPassengers;i++) {
			testBus.addPassenger();
		}
		for(int i=0;i<remove;i++) {
			testBus.addPassenger();
		}
		int finalans = testBus.totalPassengers();
		Assert.assertEquals(finalans, totalPassengers-remove);
	}
}
