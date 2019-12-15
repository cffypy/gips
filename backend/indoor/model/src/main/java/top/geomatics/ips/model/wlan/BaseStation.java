/**
 * 
 */
package top.geomatics.ips.model.wlan;

import java.util.List;

/**
 * 
 * A device which can be uniquely identified and sends out some signal that can
 * be captured and from which certain properties can be observed.
 *
 * @author whudyj
 *
 */
public class BaseStation {
	long dbid;

	protected String id;

	protected List<SignalProperty> signalProperties;

	public BaseStation() {
		super();
	}

	public BaseStation(String id, List<SignalProperty> signalProperties) {
		super();
		this.id = id;
		this.signalProperties = signalProperties;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SignalProperty> getSignalProperties() {
		return signalProperties;
	}

	public void setSignalProperties(List<SignalProperty> signalProperties) {
		this.signalProperties = signalProperties;
	}
}
