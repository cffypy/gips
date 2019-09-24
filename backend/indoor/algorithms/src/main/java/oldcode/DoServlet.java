package oldcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/DoServlet")
public class DoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ServletInputStream is = request.getInputStream();
		byte[] b = new byte[request.getContentLength()];
		is.read(b);
		String queryString = new String(b);
		int[] Piao = new int[DBUtil.getRow()];
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> listMap = JSON.parseObject(queryString,
				new TypeReference<List<Map<String, Object>>>() {
				});
		step1: for (Map<String, Object> map : listMap) {
			list = DBUtil.doloc((String) map.get("mac"), (int) map.get("rssi"));
			if (list == null || list.size() < 1) {
				continue step1;
			}
			Piao[vote(list) - 1] += 1;// 数组
		}
		int posid = posid(Piao);
		response.setContentType("application/json;charset=UTF8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(DBUtil.getloc(posid));
		pw.flush();
		pw.close();
	}

	private int vote(List<Map<String, Object>> list) {
		int j = 9999;
		for (Map<String, Object> map : list) {
			double min = 200;
			if ((Double) map.get("drssi") < min) {
				min = (Double) map.get("drssi");
				j = (int) map.get("id");
			}
		}
		return j;
	}

	private int posid(int[] a) {
		int sub = 1000;
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] >= max) {
				max = a[i];
				sub = i;
			}
		}
		return (sub + 1);
	}
}
