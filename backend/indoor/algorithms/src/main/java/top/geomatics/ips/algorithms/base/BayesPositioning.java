/**
 * 
 */
package top.geomatics.ips.algorithms.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import top.geomatics.ips.algorithms.Context;
import top.geomatics.ips.algorithms.PositioningAlgorithm;
import top.geomatics.ips.algorithms.PositioningResult;
import top.geomatics.ips.algorithms.bayes.BayesFingerprint;
import top.geomatics.ips.database.wlan.DataManager;
import top.geomatics.ips.model.wlan.AccessPoint;
import top.geomatics.ips.model.wlan.WLANFingerprint;
import top.geomatics.ips.model.wlan.WLANMeasurement;

/**
 * The Bayes positioning algorithm.
 * 
 * @author whudyj
 *
 */
public class BayesPositioning implements PositioningAlgorithm {

	Context context;
	List<BayesFingerprint> fps;

	public Entry<BayesFingerprint, Integer>[] result;

	public BayesPositioning(Context context) {

		this.context = context;

	}

	/**
	 * 
	 */
	@Override
	public PositioningResult calculatePosition(WLANMeasurement measurements) {

		DataManager dm = context.getDataManager();

		List<String> bssids = new ArrayList<String>();

		// Sets the list of bssids for which we will need fingerprints that
		// contain any of these bssids
		for (AccessPoint ap : measurements.getLevels().keySet()) {

			bssids.add(ap.getBSSID());
		}

		// Get the fingerprints from the datastore
		List<WLANFingerprint> fps = dm.getTrainingDataWithBSSIDs(bssids);

		List<BayesFingerprint> bayesFps = new ArrayList<BayesFingerprint>();

		// Create new bayes fingerprints from the retrieved fps
		for (WLANFingerprint fp : fps) {

			bayesFps.add(new BayesFingerprint(fp));
		}

		BayesFingerprint bayesFp = null;
		int lowestSum = 0;

		Map<BayesFingerprint, Integer> probabilities = new HashMap<BayesFingerprint, Integer>();

		// Gets the propabilities from the bayes fingerprints (interval sum).
		// Lowest sum is saved.
		for (BayesFingerprint fp : bayesFps) {

			int sum = fp.getIntervalSum(measurements);

			probabilities.put(fp, sum);

			if ((bayesFp == null) || (sum > lowestSum)) {

				bayesFp = fp;
				lowestSum = sum;
			}
		}

		BayesQuickSort quickSort = new BayesQuickSort();

		result = quickSort.sort(probabilities);

		PositioningResult result = new PositioningResult(bayesFp.getPosition().getX(), bayesFp.getPosition().getY());

		return result;
	}

}
