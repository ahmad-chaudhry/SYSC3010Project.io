package sysc3010Project;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class testDistributed extends TestCase{
	
	BusCenter testBC = new BusCenter();
	private InetAddress address;
	private int port;
	
	@Test 
	void testPassBoarding() throws UnknownHostException{
		Bus testBus = new Bus();
		testBus.addPassenger();
		int BusBoarding = testBus.getNumberOfPassengersEntering();
		testBC.UDPReceive(128);
		testBus.UDPSendadd(testBus, InetAddress.getByName("172.17.139.42"), 128);	
		int BCBoarding = testBC.getTotalPassengers();
		System.out.println(BusBoarding + " "+ BCBoarding);
		Assert.assertEquals(BusBoarding, BCBoarding);
		
	}
	
	@Test
	void testPassLeaving() throws UnknownHostException {
		Bus testBus = new Bus();
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.removePassenger();
		int BusLeaving = testBus.totalPassengers();
		testBC.UDPReceive(131);
		testBus.UDPSendremove(testBus, InetAddress.getByName("172.17.139.42"), 131);	
		int BCLeaving = testBC.getTotalPassengers();
		Assert.assertEquals(BusLeaving, BCLeaving);
		}
	
	@Test
	void MutliPass() {
		int zero = 0;
		Bus testBus = new Bus();
		testBus.addPassenger();
		testBus.removePassenger();
		testBC.UDPReceive(port);
		testBus.UDPSendadd(testBus, address, port);
		int BCpass = testBC.getTotalPassengers();
		Assert.assertEquals(zero,BCpass);
	}
}