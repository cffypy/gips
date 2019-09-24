/**
 * 
 */
package top.geomatics.ips.algorithms.knn;

import java.util.List;

import top.geomatics.ips.algorithms.PositioningResult;
import top.geomatics.ips.model.wlan.WLANFingerprint;

/**
 * 
 * A result returned from the nearest neighbors algorithm.
 * 
 * @author whudyj
 *
 */
public class NNResults extends PositioningResult {

	WLANFingerprint fp;

	List<DistanceResult> results;

	public NNResults() {
		super();
	}

	public NNResults(List<DistanceResult> results) {
		super(results.get(0).pos);

		this.results = results;
	}

	public WLANFingerprint getFp() {
		return fp;
	}

	public void setFp(WLANFingerprint fp) {
		this.fp = fp;
	}

	public List<DistanceResult> getResults() {
		return results;
	}

	public void setResults(List<DistanceResult> results) {
		this.results = results;
	}
}
