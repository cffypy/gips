package oldcode;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import com.alibaba.fastjson.JSON;

public class DBUtil {
	private static Connection conn = null;
	private static PreparedStatement pmt = null;

	public static void innit() throws IOException, ClassNotFoundException, SQLException {
		Properties props = new Properties();
		props.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
		Class.forName(props.getProperty("driverclass"));
		conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"),
				props.getProperty("password"));
	}

	public static int getRow() {
		int a = 0;
		try {
			String sql = "select count(*) from pos";
			innit();
			pmt = conn.prepareStatement(sql);
			ResultSet rs = pmt.executeQuery();
			if (rs.next()) {
				a = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public static List<Map<String, Object>> doloc(String mac, int rssi) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			String sql = "select * from posap where mac=?";
			innit();
			pmt = conn.prepareStatement(sql);
			pmt.setString(1, mac);
			ResultSet rs = pmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", rs.getInt("aid"));
				map.put("drssi", Math.abs(rs.getDouble("rssi") - rssi));
				list.add(map);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Destroy();
		}
		return list;
	}

	public static String getloc(int id) {
		Map<String, Object> map = new HashMap<>();
		try {
			String sql = "select * from pos where pointno=?";
			innit();
			pmt = conn.prepareStatement(sql);
			pmt.setInt(1, id);
			ResultSet rs = pmt.executeQuery();

			if (rs.next()) {
				map.put("lon", rs.getDouble("poslon"));
				map.put("lat", rs.getDouble("poslat"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Destroy();
		}
		String jsonstr = JSON.toJSONString(map);
		return jsonstr;
	}

	public static void Destroy() {
		try {
			if (pmt != null) {
				pmt.close();
				pmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
