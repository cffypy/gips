package top.geomatics.ips.utils.transform;

import org.junit.Test;

public class Shapefiles2GeopackageTest {

	@Test
	public void testExecute() {
		String dir = "D:\\ips\\data\\shilintong_shp\\1F";
		String gpkg = dir + ".gpkg";
		Shapefiles2Geopackage s2g = new Shapefiles2Geopackage(dir,gpkg);
		s2g.execute();
	}

}
