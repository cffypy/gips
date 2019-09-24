package top.geomatics.ips.model.vision;

/**
 * @author gj
 *
 *	图像的外方位元素
 */
public class ExteriorParameter {
	
	private Double tx;

	private Double ty;
	
	private Double tz;
	
	private Double qx;
	
	private Double qy;
	
	private Double qz;
	
	private Double qw;
	
	public ExteriorParameter() {
		super();
	}
	
	public ExteriorParameter(Double tx, Double ty, Double tz, Double qx, Double qy, Double qz, Double qw) {
		

		super();
		this.tx = tx;
		this.ty = ty;
		this.tz = tz;
		this.qx = qx;
		this.qy = qy;
		this.qz = qz;
		this.qw = qw;
		
		
	}
	

	public Double getTx() {
		return tx;
	}

	public void setTx(Double tx) {
		this.tx = tx;
	}

	public Double getTy() {
		return ty;
	}

	public void setTy(Double ty) {
		this.ty = ty;
	}

	public Double getTz() {
		return tz;
	}

	public void setTz(Double tz) {
		this.tz = tz;
	}

	public Double getQx() {
		return qx;
	}

	public void setQx(Double qx) {
		this.qx = qx;
	}

	public Double getQy() {
		return qy;
	}

	public void setQy(Double qy) {
		this.qy = qy;
	}

	public Double getQz() {
		return qz;
	}

	public void setQz(Double qz) {
		this.qz = qz;
	}

	public Double getQw() {
		return qw;
	}

	public void setQw(Double qw) {
		this.qw = qw;
	}
}
