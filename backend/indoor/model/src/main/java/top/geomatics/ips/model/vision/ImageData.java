/**
 * 
 */
package top.geomatics.ips.model.vision;

/**
 * @author whudyj
 *
 */
public class ImageData {
	//图像数据类型编码，内容为：JPEG、JPEG 2000有损、JPEG 2000 无损。建议采用JPEG编码。
	private String dataEncodedStyle;
	//图像宽，单位：像素。
	private Integer imageWidth;
	//图像高，单位：像素。
	private Integer imageHeight;
	//图像颜色空间编码，内容为：RGB24（24位RGB）、Gray8（8位灰度）、自定义。建议采用Gray8。
	private String imageColor;
	//图像数据。类型：JPEG或JPEG2000标准。建议采用JPEG。
	private Object data;
	
	
	public String getDataEncodedStyle() {
		return dataEncodedStyle;
	}
	public void setDataEncodedStyle(String dataEncodedStyle) {
		this.dataEncodedStyle = dataEncodedStyle;
	}
	public Integer getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}
	public Integer getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}
	public String getImageColor() {
		return imageColor;
	}
	public void setImageColor(String imageColor) {
		this.imageColor = imageColor;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}


}
