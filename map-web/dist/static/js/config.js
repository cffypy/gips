const DOMAIN = "http://localhost:8007";
// const GEOSERVER_URL = "http://localhost:9010";
const GEOSERVER_URL = "http://119.3.72.23:8085";

const MAPCFG = {
  //室内地图矢量数据
  indoorVectorUrl:GEOSERVER_URL + "/geoserver/gwc/service/tms/1.0.0/indoor%3Aunit@EPSG%3A900913@pbf/{z}/{x}/{y}.pbf",
  //室内栅格数据
  indoorTileUrl:GEOSERVER_URL + "/geoserver/gwc/service/tms/1.0.0/indoor%3Aindoor-slt@EPSG%3A900913@png/{z}/{x}/{y}.png",
  // indoorLayerUrl:DOMAIN + "/static/data/floorData.json",
  OSMUrl:"https://c.tile.openstreetmap.org/{z}/{x}/{y}.png",//osm地图
  gMapUrl:'https://wprd03.is.autonavi.com/appmaptile?style=7&x={x}&y={y}&z={z}',//高德地图
};
