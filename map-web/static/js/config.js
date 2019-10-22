const DOMAIN = "http://localhost:8007";
const GEOSERVER_URL = DOMAIN+":9010";

const MAPCFG = {
  // indoorLayerUrl:GEOSERVER_URL + "/geoserver/gwc/service/tms/1.0.0/liesmars%3Aboundary@EPSG%3A900913@pbf/{z}/{x}/{y}.pbf",
  indoorLayerUrl:DOMAIN + "/static/data/floorData.json",
  OSMUrl:"https://c.tile.openstreetmap.org/{z}/{x}/{y}.png",//osm地图
  gMapUrl:'https://wprd03.is.autonavi.com/appmaptile?style=7&x={x}&y={y}&z={z}',//高德地图
};
