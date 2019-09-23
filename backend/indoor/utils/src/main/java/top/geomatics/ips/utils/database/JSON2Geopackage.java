/**
 * 
 */
package top.geomatics.ips.utils.database;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.Geometries;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geopkg.Entry;
import org.geotools.geopkg.FeatureEntry;
import org.geotools.geopkg.GeoPackage;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.alibaba.fastjson.JSON;

import top.geomatics.ips.model.wifi.Fingerprinter;
import top.geomatics.ips.utils.file.FileUtils;

/**
 * @author whudyj
 * 
 *         <i>json格式数据转换到Geopackage</i>
 *
 */
public class JSON2Geopackage {
	private String jsonFilePath;
	private String gpkgFilePath;
	private List<Fingerprinter> wifi;
	private GeoPackage geopkg = null;
	// CGCS2000经纬度：EPSG:4490
	private static final Integer epsgRSID = 4490;
	private CoordinateReferenceSystem crsTarget = null;
	// 几何字段名
	private static final String GEOMETRY_COL_NAME = "geometry";

	public JSON2Geopackage(String jsonFilePath, String gpkgFilePath) {
		super();
		this.jsonFilePath = jsonFilePath;
		this.gpkgFilePath = gpkgFilePath;
		// 设置坐标系统
		try {
			crsTarget = CRS.decode("EPSG:4490");
		} catch (NoSuchAuthorityCodeException e1) {
			e1.printStackTrace();
		} catch (FactoryException e1) {
			e1.printStackTrace();
		}
	}

	private List<String> getJsonDirectories(String path) {

		// 列出应该搜索的JSON文件
		List<String> paths = new ArrayList<String>();
		File dir = new File(path);
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					String dirName = f.getAbsolutePath();
					paths.add(dirName);

				}
			}
		}
		return paths;
	}

	private List<String> getJsonFiles(String path) {

		// 列出应该搜索的JSON文件
		List<String> paths = new ArrayList<String>();
		File dir = new File(path);
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				if (!f.isDirectory()) {
					String fName = f.getName();
					if (0 != fName.compareToIgnoreCase("IbeaconFPDB.json")
							&& 0 != fName.compareToIgnoreCase("WifiFPDB.json")) {
						continue;
					}
					String ss = f.getAbsolutePath();
					paths.add(ss);

				}
			}
		}
		return paths;
	}

	private void readJson(String fn) {
		// 读取json数据
		String jsonContent = FileUtils.ReadFile(fn);

		wifi = JSON.parseArray(jsonContent, Fingerprinter.class);

	}

	private void initiateGpkg() {
		try {
			geopkg = new GeoPackage(new File(gpkgFilePath));
			geopkg.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		if (null != geopkg)
			geopkg.close();
	}

	private SimpleFeatureType createFeatureType(String tableName) {
		SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
		builder.setName(tableName);
		builder.setCRS(crsTarget); // <- Coordinate reference system

		builder.add(GEOMETRY_COL_NAME, Point.class);

		// 新增加的属性结构
		Field[] fileds = Fingerprinter.class.getDeclaredFields();
		for (Field fld : fileds) {
			// add attributes in order
			if (fld.getName().compareToIgnoreCase("fid") == 0) {
				continue;
			}
			builder.add(fld.getName(), fld.getType());
		}

		return builder.buildFeatureType();

	}

	private void createFeatures(String tableName) {
		// 一个文件对一个表
		SimpleFeatureType type = createFeatureType(tableName);

		List<SimpleFeature> features = new ArrayList<SimpleFeature>();
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

		FeatureEntry entry = new FeatureEntry();
		entry.setDataType(Entry.DataType.Feature);
		entry.setSrid(epsgRSID);
		entry.setTableName(tableName);
		entry.setGeometryColumn(GEOMETRY_COL_NAME);
		entry.setGeometryType(Geometries.POINT);

		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(type);

		for (Fingerprinter fp : wifi) {
			// 几何
			Double latitude = fp.getX();
			Double longitude = fp.getY();
			Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
			featureBuilder.add(point);

			// 属性
			featureBuilder.add(fp.getPointNumber());
			featureBuilder.add(fp.getX());
			featureBuilder.add(fp.getY());
			featureBuilder.add(fp.getSamples());
			featureBuilder.add(fp.getAbove70());
			featureBuilder.add(fp.getFpInfos());

			SimpleFeature feature = featureBuilder.buildFeature(null);
			features.add(feature);
		}

		try {
			geopkg.add(entry, new ListFeatureCollection(type, features));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void execute() {
		initiateGpkg();
		List<String> jsonPaths = getJsonDirectories(this.jsonFilePath);
		for (String path : jsonPaths) {
			File p = new File(path);
			String pre = p.getName();
			List<String> fs = getJsonFiles(path);
			for (String f : fs) {
				readJson(f);
				File t = new File(f);
				String n = t.getName();
				String suf = n.substring(0, n.indexOf("."));
				String tableName = pre + "_" + suf;
				createFeatures(tableName);
			}
		}

		close();
	}

}
