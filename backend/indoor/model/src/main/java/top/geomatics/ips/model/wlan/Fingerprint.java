/**
 * 
 */
package top.geomatics.ips.model.wlan;

/**
 * A fingerprint for doing scene analysis. Represents a measurement on a
 * position on an indoor map.
 * 
 * @author whudyj
 *
 */
public class Fingerprint {
	protected Position position;

	protected Measurement measurement;

	public Fingerprint() {
		super();
	}

	public Fingerprint(Position position, Measurement measurements) {
		super();
		this.position = position;
		this.measurement = measurements;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}
}
