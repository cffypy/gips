/**
 * 
 */
package top.geomatics.ips.model.wlan;

import java.util.List;

/**
 * <i>信号测量值</i>
 *  A list of measured values.
 * 
 * @author whudyj
 *
 */
public class SignalMeasurementValues {
	protected List<Double> values;

	public SignalMeasurementValues() {
		super();
	}

	public SignalMeasurementValues(List<Double> values) {
		super();
		this.values = values;
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}
}
