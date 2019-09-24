/**
 * 
 */
package top.geomatics.ips.algorithms.base;

import java.util.Map.Entry;

import top.geomatics.ips.algorithms.knn.DistanceResult;
import top.geomatics.ips.model.wlan.WLANFingerprint;

/**
 * A simple insertion sort.
 * 
 * Adapated from pseudocode found at http://en.wikipedia.org/wiki/Insertion_sort
 * 
 * @author whudyj
 *
 */
public class InsertionSort {
	private Entry<WLANFingerprint, DistanceResult>[] entries;

	public Entry<WLANFingerprint, DistanceResult>[] sort(Entry<WLANFingerprint, DistanceResult>[] values) {

		if (values == null || values.length == 0) {
			return values;
		}

		this.entries = values;

		return entries;

	}

	public void insertionSort() {

		int length = entries.length;

		for (int i = 1; i < length; i++) {

			Entry<WLANFingerprint, DistanceResult> item = entries[i];
			int iHole = i;

			while ((iHole > 0) && (entries[iHole - 1].getValue().distance > item.getValue().distance)) {

				entries[iHole] = entries[iHole--];

			}

			entries[iHole] = item;
		}
	}
}
