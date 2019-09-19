/**
 * 
 */
package top.geomatics.ips.model.base;

/**
 * @author whudyj
 *
 */
public class RSS {
	//Mac地址
	String Mac;
	//信号强度
	Integer rss;
	
	
	public RSS() {
		super();
	}


	public RSS(String mac, Integer rss) {
		super();
		Mac = mac;
		this.rss = rss;
	}


	public String getMac() {
		return Mac;
	}


	public void setMac(String mac) {
		Mac = mac;
	}


	public Integer getRss() {
		return rss;
	}


	public void setRss(Integer rss) {
		this.rss = rss;
	}
	
	

}
