/**
 * 
 */
package top.geomatics.ips.model.base;

/**
 * @author whudyj
 *
 */
public class Scene {
	// 场景，内容为：Null（空），Indoor（室内），Outdoor（室外），SemiIO（室内外交界）。
	private String context;
	// 场景识别精度。
	private Double contextAccuracy;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Double getContextAccuracy() {
		return contextAccuracy;
	}

	public void setContextAccuracy(Double contextAccuracy) {
		this.contextAccuracy = contextAccuracy;
	}

}
