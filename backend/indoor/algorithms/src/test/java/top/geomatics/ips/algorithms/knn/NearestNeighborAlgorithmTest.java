package top.geomatics.ips.algorithms.knn;

import org.junit.Before;
import org.junit.Test;
import top.geomatics.ips.algorithms.Context;
import top.geomatics.ips.algorithms.PositioningResult;
import top.geomatics.ips.database.wlan.DataManager;
import top.geomatics.ips.model.wlan.AccessPoint;
import top.geomatics.ips.model.wlan.AccessPointPowerLevels;
import top.geomatics.ips.model.wlan.WLANMeasurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		Map<String, Double> temp = new HashMap<String, Double>();
		List<String> macs = new ArrayList<String>();
		List<Double> rss = new ArrayList<Double>();
		List<Double> xList = new ArrayList<Double>();
		List<Double> yList = new ArrayList<Double>();
		// Point No 1
		temp.put("0c:73:eb:d0:13:d8", 60.57142857142857);
		macs.add("0c:73:eb:d0:13:d8");
		rss.add(60.57142857142857);
		xList.add(114.52582311);
		yList.add(30.46998429);
		// Point No 4
		temp.put("74:da:da:98:74:34", 66.83333333333336);
		macs.add("74:da:da:98:74:34");
		rss.add(66.83333333333336);
		xList.add(114.52582534);
		yList.add(30.4699031);
		// Point No 9
		temp.put("74:da:da:c1:02:8e", 73.00000000000001);
		macs.add("74:da:da:c1:02:8e");
		rss.add(73.00000000000001);
		xList.add(114.52584836);
		yList.add(30.46992603);
		// Point No 16
		temp.put("0c:73:eb:d0:13:d8", 73.5);
		macs.add("0c:73:eb:d0:13:d8");
		rss.add(73.5);
		xList.add(114.52587419);
		yList.add(30.46988038);
		// Point No 23
		temp.put("74:da:da:c1:3a:8d", 58.0);
		macs.add("74:da:da:c1:3a:8d");
		rss.add(58.0);
		xList.add(114.52590904);
		yList.add(30.46990158);
		// Point No 58
		temp.put("0c:73:eb:d0:11:f8", 46.714285714285715);
		macs.add("0c:73:eb:d0:11:f8");
		rss.add(46.714285714285715);
		xList.add(114.52606508);
		yList.add(30.4698864);
		// Point No 100
		temp.put("74:da:da:c1:48:be", 58.8095238095238);
		macs.add("74:da:da:c1:48:be");
		rss.add(58.8095238095238);
		xList.add(114.52621859);
		yList.add(30.46993807);
		// Point No 120
		temp.put("0c:73:eb:d0:76:30", 78.0);
		macs.add("0c:73:eb:d0:76:30");
		rss.add(78.0);
		xList.add(114.52627686);
		yList.add(30.46992014);
		// Point No 151
		temp.put("74:da:da:c1:4a:f5", 74.125);
		macs.add("74:da:da:c1:4a:f5");
		rss.add(74.125);
		xList.add(114.52636633);
		yList.add(30.46994365);
		// Point No 165
		temp.put("0c:73:eb:d0:7c:38", 80.0);

		macs.add("0c:73:eb:d0:7c:38");
		rss.add(80.0);
		xList.add(114.52643244);
		yList.add(30.46987645);

		List<Double> biasX = new ArrayList<Double>();
		List<Double> biasY = new ArrayList<Double>();
		for (int i = 0; i < macs.size(); i++) {
			Map<AccessPoint, AccessPointPowerLevels> levels = new HashMap<AccessPoint, AccessPointPowerLevels>();

			AccessPoint ap = new AccessPoint();
			String mac = macs.get(i);
			ap.setBSSID(mac);
			List<Double> rssis = new ArrayList<Double>();
			Double meanRSSI = rss.get(i);
			rssis.add(meanRSSI);
			AccessPointPowerLevels apl = new AccessPointPowerLevels(rssis);

			levels.put(ap, apl);

			WLANMeasurement measurements = new WLANMeasurement(levels);

			PositioningResult result = al.calculatePosition(measurements);

			Double x = result.getX();
			Double y = result.getY();

			System.out.println("x:" + x);
			System.out.println("y:" + y);
			Double bx = x - xList.get(i);
			Double by = y - yList.get(i);
			System.out.println("bx:" + bx);
			System.out.println("by:" + by);

			biasX.add(bx);
			biasY.add(by);
		}

	}

}
