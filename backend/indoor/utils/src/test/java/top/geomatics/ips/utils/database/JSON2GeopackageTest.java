package top.geomatics.ips.utils.database;

import org.junit.Before;
import org.junit.Test;

public class JSON2GeopackageTest {
	private JSON2Geopackage j2p;
	
	@Before
	public void setup() {
		j2p = new JSON2Geopackage("D:\\ips\\data\\database\\json\\WifiFPfloor.json","");
	}

	@Test
	public void testExecute() {
		j2p.execute();
	}

}
