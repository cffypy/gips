package top.geomatics.ips.utils.transform;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.Geometries;
import org.geotools.geometry.jts.JTS;
import org.geotools.geopkg.Entry;
import org.geotools.geopkg.FeatureEntry;
import org.geotools.geopkg.GeoPackage;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author whudyj
 *
 */
public class Shapefiles2Geopackage {
	// 添加slf4j日志实例对象
	private final static Logger logger = LoggerFactory.getLogger(Shapefiles2Geopackage.class);

	// 一些参数设置
	private Map<String, String> settings = null;

	public Map<String, String> getSettings() {
		return settings;
	}

	public void setSettings(Map<String, String> settings) {
		this.settings = settings;
	}

	private static final String SRID = "srid";
	private static final String EPSG_SRID_STRING = "EPSG:4490";// 4547,4326
	private static final Integer EPSG_SRID = 4490;// 4547,4326
	private static final String CREATE_INDEX = "create_spatial_index";
	private boolean isCreateIndex = true;

	// 目标文件
	private String geopackageName = "";
	private GeoPackage geopkg = null;

	// 原始文件
	private List<String> shapefileNames = null;

	private DataStore dataStore = null;

	private CoordinateReferenceSystem crsSource = null;
	private CoordinateReferenceSystem crsTarget = null;
	// 投影转换
	private MathTransform transform = null;

	// 构造函数
	public Shapefiles2Geopackage(List<String> shapefileNames, String geopackageName, Map<String, String> settings) {
		super();
		this.shapefileNames = shapefileNames;
		this.geopackageName = geopackageName;
		this.settings = settings;
	}

	public Shapefiles2Geopackage(String shapefileDir, String geopackageName) {
		super();

		String[] extensions = new String[] { "shp" };
		File dir = new File(shapefileDir);
		Collection<File> shpFiles = FileUtils.listFiles(dir, extensions, false);

		this.shapefileNames = new ArrayList<>();
		for (File f : shpFiles) {
			this.shapefileNames.add(f.getAbsolutePath());
		}

		this.geopackageName = geopackageName;
	}

	public Shapefiles2Geopackage(List<String> shapefileNames) {
		super();
		this.shapefileNames = shapefileNames;
	}

	private boolean readShapefile(String shapefileName) {
		File file = new File(shapefileName);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("url", file.toURI().toURL());
			dataStore = DataStoreFinder.getDataStore(map);
			if (dataStore instanceof ShapefileDataStore) {
				((ShapefileDataStore) dataStore).setCharset(Charset.forName("GBK"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 日志
			String logMsgString = String.format("打开shapefile文件：%s 失败！", shapefileName);
			logger.error(logMsgString);
			return false;
		}
		return true;
	}

	private boolean initiateGpkg() {

		// 空间参考
		/*
		 * CGCS2000 / 3-degree Gauss-Kruger CM 114E EPSG:4547
		 * 
		 * Area of use: China - onshore between 112°30'E and 115°30'E.
		 */

		try {
			crsSource = CRS.decode("EPSG:4509");
			crsTarget = CRS.decode(EPSG_SRID_STRING);
			// 投影转换
			transform = CRS.findMathTransform(crsSource, crsTarget);
		} catch (Exception e1) {
			e1.printStackTrace();
			// 日志
			String logMsgString = String.format("解析空间参失败！");
			logger.error(logMsgString);
			return false;
		}

		try {
			File gf = new File(this.geopackageName);
			if (gf.exists()) {
				// 日志
				String logMsgString = String.format("geopackage文件：%s 已经存在！", this.geopackageName);
				logger.error(logMsgString);
				return false;
			}
			geopkg = new GeoPackage(gf);
			geopkg.init();
		} catch (IOException e) {
			e.printStackTrace();
			// 日志
			String logMsgString = String.format("初始化geopackage文件：%s 失败！", this.geopackageName);
			logger.error(logMsgString);
			return false;
		}
		if (this.settings != null && this.settings.containsKey(CREATE_INDEX)) {
			isCreateIndex = Boolean.parseBoolean(this.settings.get(CREATE_INDEX));
		}

		return true;
	}

	private void close() {
		geopkg.close();
	}

	/**
	 * @return SimpleFeatureType 返回创建的要素类型
	 */
	private SimpleFeatureType createFeatureType(String featureName, SimpleFeatureType sfType) {

		SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
		// builder.setName(sfType.getTypeName());
		builder.setName(featureName);
		builder.setCRS(crsTarget); // <- Coordinate reference system

		// 原属性结构
		List<AttributeDescriptor> descriptors = sfType.getAttributeDescriptors();
		builder.addAll(descriptors);

		return builder.buildFeatureType();
	}

	public boolean execute() {
		if (this.shapefileNames == null || this.shapefileNames.size() < 1) {
			return false;
		}

		// 初始化目标文件
		if (initiateGpkg() == false || geopkg == null) {
			return false;
		}

		for (String name : this.shapefileNames) {
			// 读数据文件
			if (readShapefile(name) == false || dataStore == null) {
				continue;
			}
			String featureName = name.substring(name.lastIndexOf("\\") + 1, name.lastIndexOf("."));

			String typeName;
			FeatureSource<SimpleFeatureType, SimpleFeature> source = null;
			FeatureCollection<SimpleFeatureType, SimpleFeature> features = null;
			SimpleFeatureCollection featureCollection = null;
			SimpleFeatureType sfType = null;
			SimpleFeatureType targetSFType = null;
			FeatureEntry entry = new FeatureEntry();
			try {
				typeName = dataStore.getTypeNames()[0];
				source = dataStore.getFeatureSource(typeName);
				features = source.getFeatures();
			} catch (IOException e) {
				e.printStackTrace();
				// 日志
				String logMsgString = String.format("读取shapefile文件：%s 失败！", name);
				logger.error(logMsgString);
				continue;
			}
			sfType = features.getSchema();
			targetSFType = createFeatureType(featureName, sfType);
			if (features instanceof SimpleFeatureCollection) {
				featureCollection = (SimpleFeatureCollection) features;
			}
			entry.setDataType(Entry.DataType.Feature);
			entry.setSrid(EPSG_SRID);
			// entry.setTableName(sfType.getTypeName());
			entry.setTableName(featureName);
			// 几何字段
			String geoNameString = sfType.getGeometryDescriptor().getName().toString();
			entry.setGeometryColumn(geoNameString);
			// 几何类型
			GeometryType gType = sfType.getGeometryDescriptor().getType();
			String geoType = gType.getName().toString();
			entry.setGeometryType(Geometries.getForName(geoType));

			SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(targetSFType);
			List<SimpleFeature> newFeas = new ArrayList<SimpleFeature>();

			SimpleFeatureIterator iterator = featureCollection.features();
			while (iterator.hasNext()) {
				SimpleFeature aFeature = iterator.next();
				SimpleFeature targetFeature = null;
				featureBuilder.addAll(aFeature.getAttributes());
				// 坐标转换
				Geometry oldGeometry = (Geometry) aFeature.getDefaultGeometry();
				Geometry newGeometry = xy2lonlat(oldGeometry);

				featureBuilder.set(geoNameString, newGeometry);

				targetFeature = featureBuilder.buildFeature(null);
				newFeas.add(targetFeature);
			}
			try {
				geopkg.add(entry, new ListFeatureCollection(targetSFType, newFeas));
				if (isCreateIndex) {
					geopkg.createSpatialIndex(entry);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			iterator.close();
			////////////////////
		}

		close();

		return true;

	}

	/**
	 * 高斯坐标转换为经纬度
	 * 
	 * @param geom
	 * @return
	 */
	private Geometry xy2lonlat(Geometry geom) {
		Geometry geometry = null;
		try {
			// 投影转换
			geometry = JTS.transform(geom, transform);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geometry;
	}

}
