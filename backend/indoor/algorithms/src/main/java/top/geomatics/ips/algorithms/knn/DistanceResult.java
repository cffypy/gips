/**
 * 
 */
package top.geomatics.ips.algorithms.knn;

import top.geomatics.ips.model.wlan.Position;
import top.geomatics.ips.model.wlan.WLANFingerprint;

/**
 * 
 * A result from the nearest neighbors algorithm. Contains the distance found to
 * a fingerprint and the number of ssids that matched.
 * 
 * @author whudyj
 *
 */
public class DistanceResult {
	public double distance;

	public int nrOfMatchedBssids;

	public Position pos;

	public DistanceResult() {
		super();
	}

	public DistanceResult(double d, int nrOfMatchedBssids, WLANFingerprint fp) {
		super();
		this.distance = d;
		this.nrOfMatchedBssids = nrOfMatchedBssids;
		// this.fingerprint = fp;
		this.pos = fp.getPosition();
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getNrOfMatchedBssids() {
		return nrOfMatchedBssids;
	}

	public void setNrOfMatchedBssids(int nrOfMatchedBssids) {
		this.nrOfMatchedBssids = nrOfMatchedBssids;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
}
