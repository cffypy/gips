/**
 * 
 */
package top.geomatics.ips.model.base;

import lombok.Data;

/**
 * @author whudyj
 *定位场景
 */
@Data
public class Scene {
	// 场景，内容为：Null（空），Indoor（室内），Outdoor（室外），SemiIO（室内外交界）。
	private String context;
	// 场景识别精度。
	private Double contextAccuracy;


}
