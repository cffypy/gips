/**
 * 
 */
package top.geomatics.ips.database.wlan;

import java.util.ArrayList;
import java.util.List;

import top.geomatics.ips.model.wlan.AccessPoint;
import top.geomatics.ips.model.wlan.Position;
import top.geomatics.ips.model.wlan.WLANFingerprint;

/**
 * @author whudyj
 *
 */
public class DataManager {
	/**
	 * Retrieve all the WLAN samples from the datastore
	 * 
	 * @return
	 */
	public List<WLANFingerprint> getSampleData() {

		List<WLANFingerprint> fps = null;

		return fps;
	}

	/**
	 * Find the fingerprint of a certain location
	 * 
	 * @param p The position for which to find the WLAN fingerprint
	 * @return
	 */
	public WLANFingerprint findFpWithLocation(Position p) {
		return null;
	}
	
	/**
	 * 
	 * Checks if a WLAN fingerprint contains an access point with a certain
	 * BSSID.
	 * 
	 * @param fp
	 *            The fingerprint to check
	 * @param bssid
	 *            The bssid of the access point
	 * @return True if an access point with that bssid is in the fingerprint.
	 *         False otherwise.
	 */
	@SuppressWarnings("unused")
	private boolean containsAp(WLANFingerprint fp, String bssid) {

		for (AccessPoint ap : fp.getMeasurement().getApMeasurements().keySet()) {

			if (ap.getBSSID().equals(bssid)) {

				return true;
			}
		}

		return false;
	}
	
	/**
	 * 
	 * Get all the WLAN fingerprints that have contain any of the access points
	 * with given bssid.
	 * 
	 * @param bssids
	 *            The bssids of the access points to find
	 * @return The WLAN fingerprints that contain any of the access points with
	 *         the bssids.
	 */
	public List<WLANFingerprint> getTrainingDataWithBSSIDs(List<String> bssids) {

		List<WLANFingerprint> unfilteredFps = this.getSampleData();

		List<WLANFingerprint> fps = new ArrayList<WLANFingerprint>();

		/**
		 * Loop over all the fingerprints and check if it contains an access
		 * point with one of the bssids.
		 */
		for (WLANFingerprint fp : unfilteredFps) {

			for (String bssid : bssids) {

				if (containsAp(fp, bssid)) {

					fps.add(fp);
					break;
				}
			}
		}

		return fps;
	}


}
