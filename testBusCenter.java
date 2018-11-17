package projectTests;

import static org.junit.jupiter.api.Assertions.*;
import java.net.InetAddress;
import org.junit.jupiter.api.Test;
import junit.framework.Assert;
import junit.framework.TestCase;
import syscThirdYear.Bus;
import syscThirdYear.BusCenter;

class testBusCenter extends TestCase{
	
	private static int passengersEntered = 5;
	private static int passengersExited = 4;
	BusCenter testBC = new BusCenter();
	String testBCZone = "green";
	private InetAddress address;
	private int port;
	private int finalAns = passengersEntered - passengersExited;
	
	@Test 
	void testUDPSend(){
		Bus testBus = new Bus();
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
	void testUpdateDatabase(int x) {
		testBC.updateDatabase(x);
		Assert.assertEquals(testBC.getDB().contains(x), true);
	}
}
