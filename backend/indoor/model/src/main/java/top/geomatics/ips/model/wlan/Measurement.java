/**
 * 
 */
package top.geomatics.ips.model.wlan;

import java.util.Map;

/**
 * 
 * Contains the different Signal measurements of certain Base stations.
 * 
 * @author whudyj
 *
 */
public class Measurement {
	protected Map<BaseStation, SignalMeasurement> measurements;

	public Measurement() {

		super();
	}

	public Measurement(Map<BaseStation, SignalMeasurement> measurements) {
		super();
		this.measurements = measurements;
	}

	public Map<BaseStation, SignalMeasurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Map<BaseStation, SignalMeasurement> measurements) {
		this.measurements = measurements;
	}
}
