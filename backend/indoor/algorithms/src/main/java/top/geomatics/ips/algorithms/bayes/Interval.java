/**
 * 
 */
package top.geomatics.ips.algorithms.bayes;

/**
 * * An interval to check if a value is in such the interval.
 * 
 * Used as "anonymous functions".
 * 
 * @author whudyj
 *
 */
public interface Interval {
	/**
	 * 
	 * @param level The level to check
	 * @return A boolean denoting if the value is in the interval
	 */
	public boolean checkValue(double level);
}
