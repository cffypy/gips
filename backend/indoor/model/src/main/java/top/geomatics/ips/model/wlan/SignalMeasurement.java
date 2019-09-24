/**
 * 
 */
package top.geomatics.ips.model.wlan;

import java.util.Map;

/**
 * A measurement of certain signal properties with the captured measurement
 * values. For each property there can be a number of captured values since this
 * allows us to for example take the average of the values or get other relevant
 * information (SignalMeasurementValues).
 * 
 * @author whudyj
 *
 */
public class SignalMeasurement {
	protected Map<SignalProperty, SignalMeasurementValues> signalMeasurements;

	public SignalMeasurement() {

		super();
	}

	public SignalMeasurement(SignalProperty signalProperty,
			Map<SignalProperty, SignalMeasurementValues> signalMeasurement) {

		super();
		this.signalMeasurements = signalMeasurement;
	}

	public Map<SignalProperty, SignalMeasurementValues> getSignalMeasurement() {

		return signalMeasurements;
	}

	public void setSignalMeasurement(
			Map<SignalProperty, SignalMeasurementValues> signalMeasurement) {

		this.signalMeasurements = signalMeasurement;
	}
}
