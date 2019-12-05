/**
 * 
 */
package top.geomatics.ips.utils.image;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

//import org.bytedeco.javacpp.opencv_core.KeyPoint;
//import org.bytedeco.javacpp.opencv_core.Mat;
//import org.bytedeco.javacpp.opencv_core.Point2f;
//import org.bytedeco.javacpp.opencv_core.Point3d;

import org.opencv.core.Mat;
import org.opencv.core.KeyPoint;
import org.opencv.core.Point;
import org.opencv.core.Point3;
import org.opencv.core.Core;

import com.alibaba.fastjson.JSON;

import top.geomatics.ips.utils.file.FileUtils;

/**
 * @author whudyj
 *
 */
public class ImageDataReader {
	private List<Data> objs = new ArrayList<Data>();

	public void loadOBJ(String filename, String wr) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		File file = new File(filename);
		if (!file.exists()) {
			return;
		}
		DataInputStream is = null;
		BufferedWriter writer = getWriter(wr);
		try {
			is = new DataInputStream(new FileInputStream(file));
			int cnt = is.readInt();
			cnt = Integer.reverseBytes(cnt);

			writeLine(writer, "count: " + cnt);

			for (int i = 0; i < cnt; i++) {
				Data t = new Data();
				//读取特征点id
				t.id = Integer.reverseBytes(is.readInt());
				writeLine(writer, "id: " + t.id);

				//读取三维坐标
				double x = Double.longBitsToDouble(Long.reverseBytes(is.readLong()));
				double y = Double.longBitsToDouble(Long.reverseBytes(is.readLong()));
				double z = Double.longBitsToDouble(Long.reverseBytes(is.readLong()));
				//System.out.println("x:" + x + " y: " + y + " z: " + z);
				writeLine(writer, "x:" + x + " y: " + y + " z: " + z);

				t.pt3 = new Point3(x,y,z);

				//读取特征点个数size
				int sucnt = Integer.reverseBytes(is.readInt());
				writeLine(writer, "size: " + sucnt);
				for (int j = 0; j < sucnt; j++) {
					t.marks = new ArrayList<IMG_KEY>();
					IMG_KEY key = new IMG_KEY();

					key.IMG_ID = Integer.reverseBytes(is.readInt());
					writeLine(writer, "IMG_ID: " + key.IMG_ID);
					key.KEY_ID = Integer.reverseBytes(is.readInt());
					writeLine(writer, "KEY_ID: " + key.KEY_ID);
					float px = Float.intBitsToFloat(Integer.reverseBytes(is.readInt()));
					float py = Float.intBitsToFloat(Integer.reverseBytes(is.readInt()));
					float sz = Float.intBitsToFloat(Integer.reverseBytes(is.readInt()));
					float angle = Float.intBitsToFloat(Integer.reverseBytes(is.readInt()));
					float response = Float.intBitsToFloat(Integer.reverseBytes(is.readInt()));
					int oct = Integer.reverseBytes(is.readInt());
					int cls_id = Integer.reverseBytes(is.readInt());

					//写下每一个特征点的图像坐标、角度、层次等信息
					String c = String.format("KeyPoint: {px:%f py:%f sz:%f angle: %f response:%f oct:%d cls_id:%d }",
							px, py, sz, angle, response, oct, cls_id);

					writeLine(writer, c);
					//为data属性赋值
					key.kpt = new KeyPoint(px,py, sz, angle, response, oct, cls_id);
					t.marks.add(key);
				}

				int rows = Integer.reverseBytes(is.readInt());
				int cols = Integer.reverseBytes(is.readInt());
				int type_ = Integer.reverseBytes(is.readInt());

				//该特征描述子的结构
				String sc = String.format("Mat: {rows:%d cols:%d type:%d }", rows, cols, type_);

				writeLine(writer, sc);

				//
				t.des = new Mat(rows, cols , type_);
				long total_ = rows * t.des.step1(0);
				//byte b[] = new byte[total_];
				//int sss = is.read(b);


				//读取64维特征向量的每一维的数字
				for(int m = 0; m < cols; m++){
					float descriptor_m = Float.intBitsToFloat(Integer.reverseBytes(is.readInt()));
					t.des.put(0,m,descriptor_m);
					//String ss = String.format("descriptor:%f",descriptor_m);
					//writeLine(writer, ss);

				}

				int cx = is.readUnsignedByte();
				int cy = is.readUnsignedByte();
				int cz = is.readUnsignedByte();

				//写下特征点颜色
				sc = String.format("colors: {cx:%d cy:%d cz:%d }", cx, cy, cz);

				writeLine(writer, sc);

				t.colors = new int[3];
				t.colors[0] = cx;
				t.colors[1] = cy;
				t.colors[2] = cz;
                
				objs.add(t);
			}//读取完一个Data项
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	//获取特征描述子及三维点集
	public void loadDes(Mat desc, List<Point3> pt3List){

		int featureNum = objs.size();

		for (Data it:objs){
			desc.push_back(it.des);
			pt3List.add(it.pt3);
		}

	}

	public void write(String path) {
		String strContent = JSON.toJSONString(objs);
		FileUtils.WriteFile(path, strContent);
	}

	public void writeLine(BufferedWriter writer, String content) {
		try {
			writer.write(content);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BufferedWriter getWriter(String path) {
		BufferedWriter writer = null;
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(f);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			writer = new BufferedWriter(outputStreamWriter);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}

	//数据项，对应一个空间特征点，包含id,对应的图像特征点，描述子，三维坐标，色彩信息。
	public class Data {

		public Data() {
			super();
		}

		int id = 0;
		List<IMG_KEY> marks;
		Mat des;// 描述子
		Point3 pt3;
		int[] colors;

	}

	public class IMG_KEY {
		int IMG_ID;
		int KEY_ID;
		KeyPoint kpt;
	}
}
