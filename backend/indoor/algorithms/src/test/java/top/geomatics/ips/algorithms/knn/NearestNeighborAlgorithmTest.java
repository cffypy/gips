package top.geomatics.ips.algorithms.knn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import top.geomatics.ips.algorithms.Context;
import top.geomatics.ips.algorithms.PositioningResult;
import top.geomatics.ips.database.wlan.DataManager;
import top.geomatics.ips.model.wlan.AccessPoint;
import top.geomatics.ips.model.wlan.AccessPointPowerLevels;
import top.geomatics.ips.model.wlan.WLANMeasurement;

public class NearestNeighborAlgorithmTest {

	NearestNeighborAlgorithm al = null;

	@Before
	public void setup() {
		DataManager dataManager = new DataManager();

		Context context = new Context(dataManager);
		al = new NearestNeighborAlgorithm(context);
	}

	@Test
	public void testCalculatePosition() {

		Map<AccessPoint, AccessPointPowerLevels> levels = new HashMap<AccessPoint, AccessPointPowerLevels>();

		AccessPoint ap = new AccessPoint();
		String mac = "0c:73:eb:d0:13:d8";
		ap.setBSSID(mac);
		List<Double> rssis = new ArrayList<Double>();
		Double meanRSSI = 66.83333333333336;
		rssis.add(meanRSSI);
		AccessPointPowerLevels apl = new AccessPointPowerLevels(rssis);

		levels.put(ap, apl);

		WLANMeasurement measurements = new WLANMeasurement(levels);

		PositioningResult result = al.calculatePosition(measurements);

		System.out.println("x:" + result.getX());
		System.out.println("y:" + result.getY());
	}

}
