/**
 * 
 */
package top.geomatics.ips.model.base;

import lombok.Data;

import java.util.Date;

/**
 * @author whudyj
 *
 *
 *         <i>定位请求基本信息</i>
 */
@Data
public class RequestBase {
	// 定位请求标识。
	private String requestID;
	// 终端唯一标识
	private String deviceID;
	// 定位请求的时间。单位：毫秒。采用通用协调时（UTC）。
	private Date timeStamp;

}
