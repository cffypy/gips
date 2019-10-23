/**
 * 
 */
package top.geomatics.ips.database.wlan;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import top.geomatics.ips.model.wifi.FingerPrinterInfo;
import top.geomatics.ips.model.wifi.Fingerprinter;
import top.geomatics.ips.model.wlan.AccessPoint;
import top.geomatics.ips.model.wlan.AccessPointPowerLevels;
import top.geomatics.ips.model.wlan.Position;
import top.geomatics.ips.model.wlan.WLANFingerprint;
import top.geomatics.ips.model.wlan.WLANMeasurement;
import top.geomatics.ips.utils.file.FileUtils;

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

		List<WLANFingerprint> fps = new ArrayList<WLANFingerprint>();
		List<Fingerprinter> wifi;

		//String pathString = "D:\\ips\\data\\database\\shilintong\\floor_1";
		String pathString = "C:\\Users\\chenfa\\Desktop\\shilintong\\floor_1";
		String fn = pathString + File.separator + "WifiFPDB.json";

		// 读取json数据
		String jsonContent = FileUtils.ReadFile(fn);

		wifi = JSON.parseArray(jsonContent, Fingerprinter.class);
		for (Fingerprinter fp : wifi) {
			double x = fp.getX();
			double y = fp.getY();
			long id = Long.parseLong(fp.getPointNumber());
			Position position = new Position(x, y);
			position.setId(id);

			Map<AccessPoint, AccessPointPowerLevels> levels = new HashMap<AccessPoint, AccessPointPowerLevels>();

			String info = fp.getFpInfos();
			List<FingerPrinterInfo> infos = JSON.parseArray(info, FingerPrinterInfo.class);

			for (FingerPrinterInfo fpi : infos) {
				String mac = fpi.getMac();
				AccessPoint ap = new AccessPoint();
				ap.setBSSID(mac);
				List<Double> rssis = new ArrayList<Double>();
				Double meanRSSI = fpi.getMeanRssi();
				rssis.add(meanRSSI);
				AccessPointPowerLevels apl = new AccessPointPowerLevels(rssis);

				levels.put(ap, apl);
			}

			WLANMeasurement measurement = new WLANMeasurement(levels);
			WLANFingerprint ww = new WLANFingerprint(position, measurement);
			fps.add(ww);
		}

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
	 * Checks if a WLAN fingerprint contains an access point with a certain BSSID.
	 * 
	 * @param fp    The fingerprint to check
	 * @param bssid The bssid of the access point
	 * @return True if an access point with that bssid is in the fingerprint. False
	 *         otherwise.
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
	 * Get all the WLAN fingerprints that have contain any of the access points with
	 * given bssid.
	 * 
	 * @param bssids The bssids of the access points to find
	 * @return The WLAN fingerprints that contain any of the access points with the
	 *         bssids.
	 */
	public List<WLANFingerprint> getTrainingDataWithBSSIDs(List<String> bssids) {

		List<WLANFingerprint> unfilteredFps = this.getSampleData();

		List<WLANFingerprint> fps = new ArrayList<WLANFingerprint>();

		/**
		 * Loop over all the fingerprints and check if it contains an access point with
		 * one of the bssids.
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
