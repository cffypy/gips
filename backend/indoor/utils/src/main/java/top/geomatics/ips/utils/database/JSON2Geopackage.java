/**
 * 
 */
package top.geomatics.ips.utils.database;

import java.util.List;

import com.alibaba.fastjson.JSON;

import top.geomatics.ips.model.ibeacon.IBeaconScan;
import top.geomatics.ips.model.wifi.FPFloor;
import top.geomatics.ips.model.wifi.WiFiSacn;
import top.geomatics.ips.model.wifi.WifiSample;
import top.geomatics.ips.utils.file.FileUtils;

/**
 * @author whudyj
 * 
 * <i>json格式数据转换到Geopackage</i>
 *
 */
public class JSON2Geopackage {
	private String jsonFilePath;
	private String gpkgFilePath;
	public JSON2Geopackage(String jsonFilePath, String gpkgFilePath) {
		super();
		this.jsonFilePath = jsonFilePath;
		this.gpkgFilePath = gpkgFilePath;
	}
	
	public void execute() {
		//读取json数据
		String jsonContent = FileUtils.ReadFile(this.jsonFilePath);
		
		List<FPFloor>	wifi = JSON.parseArray(jsonContent,FPFloor.class);
		
		System.out.println(JSON.toJSONString(wifi));
		
	}

}
