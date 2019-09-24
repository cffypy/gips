/**
 * 
 */
package top.geomatics.ips.model.wlan;

import java.util.ArrayList;
import java.util.List;

/**
 * A type of base station used in WLAN that allows other wireless devices to
 * connect a wired network. It is identified by its BSSID (address), has a human
 * readable name or Service set identification (SSID), a single string denoting
 * the capabilities of the access point (for example what type of security it
 * supports), the frequency in MHz of the channel over which the client is
 * communicating with the access point and the detected signal level in dBm.
 * This is generally the information that can be found when doing a WLAN scan on
 * a mobile device.
 * 
 * @author whudyj
 *
 */
public class AccessPoint extends BaseStation {

	protected String BSSID;

	protected String SSID;

	protected int frequency;

	protected String capabilities;

	public AccessPoint() {

		super();
	};

	public AccessPoint(String bSSID, String sSID, int frequency, String capabilities) {

		super();

		// Id of the base station equals the BSSID
		setId(bSSID);

		// We can always get the RSSI from access points
		List<SignalProperty> properties = new ArrayList<SignalProperty>();
		properties.add(SignalProperty.RSSI);
		this.signalProperties = properties;

		BSSID = bSSID;
		SSID = sSID;
		this.frequency = frequency;
		this.capabilities = capabilities;
	}

	@Override
	public String getId() {

		return this.BSSID;
	}

	public String getBSSID() {
		return BSSID;
	}

	public void setBSSID(String bSSID) {
		BSSID = bSSID;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}
}
