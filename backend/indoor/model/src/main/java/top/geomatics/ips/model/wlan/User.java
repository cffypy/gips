/**
 * 
 */
package top.geomatics.ips.model.wlan;

/**
 * A user that can be identified by an ID.
 * 
 * @author whudyj
 *
 */
public class User {
	protected String id;

	public User() {

		super();
	}

	public User(String id) {

		super();
		this.id = id;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}
}
