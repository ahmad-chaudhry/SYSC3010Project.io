package projectTests;

import java.net.InetAddress;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
import syscThirdYear.Bus;
import syscThirdYear.BusCenter;

class testBusCenter extends TestCase{
	
	private InetAddress address;
	private int port;
	/**
	@Test
	void testUDPReceive() {
		BusCenter testBC = new BusCenter();
		Bus testBus = new Bus();
		testBus.addPassenger();
		testBus.addPassenger();
		testBus.UDPSend(testBus, address, port);
		testBC.UDPReceive(port);
		Assert.assertEquals(testBC.getReceivedString(), "2");
	}
	**/
	
	@Test
	void testAddBusToList(Bus x) {
		BusCenter testBC = new BusCenter();
		testBC.addBusToList(x);
		Assert.assertEquals(testBC.getBusList().contains(x), true);
	}
	/**
	 * @Test
		void testUpdateDatabase(Integer x) {
		BusCenter testBC = new BusCenter();
		//testBC.updateDatabase(x);
		Assert.assertEquals(testBC.getDB().contains(x), true);
	}
	 * @param x
	 */
	
}
