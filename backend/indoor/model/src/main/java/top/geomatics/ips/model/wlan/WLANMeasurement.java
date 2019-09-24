/**
 * 
 */
package top.geomatics.ips.model.wlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A measurement of a WLAN fingerprint. Contains the measured access point and
 * their captured power levels.
 * 
 * @author whudyj
 *
 */
public class WLANMeasurement extends Measurement {
	protected Map<AccessPoint, AccessPointPowerLevels> apMeasurements;

	public WLANMeasurement() {

		super();
	}

	public WLANMeasurement(Map<AccessPoint, AccessPointPowerLevels> levels) {

		super();

		List<SignalProperty> properties = new ArrayList<SignalProperty>();
		properties.add(SignalProperty.RSSI);

		this.apMeasurements = new HashMap<AccessPoint, AccessPointPowerLevels>();

		for (Entry<AccessPoint, AccessPointPowerLevels> entry : levels.entrySet()) {

			this.apMeasurements.put(entry.getKey(), entry.getValue());
		}
	}

	public Map<AccessPoint, AccessPointPowerLevels> getApMeasurements() {

		return apMeasurements;
	}

	/**
	 * 
	 * @return Returns a map of the average power levels of each access point in the
	 *         measurement
	 */
	public Map<AccessPoint, Integer> getLevels() {

		Map<AccessPoint, Integer> levels = new HashMap<AccessPoint, Integer>();

		for (Entry<AccessPoint, AccessPointPowerLevels> entry : apMeasurements.entrySet()) {

			AccessPoint ap = entry.getKey();
			AccessPointPowerLevels pLvls = entry.getValue();

			levels.put(ap, (int) pLvls.getAverage());
		}

		return levels;
	}

	public void put(AccessPoint ap, AccessPointPowerLevels levels) {

		this.apMeasurements.put(ap, levels);
	}

	public void setApMeasurements(Map<AccessPoint, AccessPointPowerLevels> levels) {

		this.apMeasurements = levels;

	}
}
