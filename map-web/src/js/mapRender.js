/**
 * 地图渲染模块
 * Created by lixiaochao on 2018/12/27.
 */

/**
 * 初始化地方地图2435
 * @param divId  容器id
 * @param option zoom
 * @param option center
 * @param option zoomRange
 * @param option success
 * @param option onLoad
 * @returns {*}
 */
import mapboxgl from 'mapbox-gl'
function RenderMap (divId, option) {
  // mapboxgl.accessToken = 'pk.eyJ1IjoiY2xvdmVyNzc3IiwiYSI6ImNqcWV6bzNvczF5NWs0MnQ3dHY1eWVrcG4ifQ.x5ImpzBhf155X5SUF9tXkA';

  if (divId) {
    option = option ? option : {};
    let zoom = option.zoom ? option.zoom : 10,//初始化层级
      center = option.center ? option.center : [114.30, 30.52];//地图中心
    let zoomRange = [0, 18];//地图缩放层级
    if (option.zoomRange) {
      if (option.zoomRange[0] > option.zoomRange[1]) {
        throw 'zoomRange[0]应小于zoomRange[1]'
      } else {
        zoomRange = option.zoomRange
      }
    }

    //构造地图对象实例,并添加到id为map的div容器中
    var map = new mapboxgl.Map({
      // style:'mapbox://styles/clover777/cjzi59dmh0ipi1cp7quv8dn2t',
      container: divId,
      style:{
        "version": 8,
        "sources": {
          "osm-tiles": {
            "type": "raster",
            'tiles': [
              MAPCFG.OSMUrl
            ],
            'tileSize': 256
          },

          //高德地图
          "gaode-tiles":{
            "type": "raster",
            "tiles": [MAPCFG.gMapUrl],
            "tileSize": 256
          },
          /*"indoor": {
           "type": "vector",
           "scheme": "tms",
           "tiles": [
             MAPCFG['indoorLayerUrl']
           ]
         },*/
        },
        "layers": [
          // OSM地图
          {
            "id": "osm",
            "type": "raster",
            "source": "osm-tiles",
            "paint":{
            },
            "layout":{
              "visibility":"none"
            }
          },
          {
            "id": "layer-gaode",
            "type": "raster",
            "source": "gaode-tiles",
            "minzoom": 2,
            "maxzoom": 18,
            "layout":{
              // "visibility":"none"
            }
          },
          // 室内地图
         /* {
            "id": "border",
            "type": "line",
            "source": "indoor",
            "source-layer": "boundary",
            "layout": {
              "line-join": "round",
              "line-cap": "round"
            },
            "paint": {
              "line-color": "#3031ff",
              "line-width": {
                "stops": [[6,0.4], [8, 0.6], [13, 1]]
              },
              // "line-dasharray": [1.5,3]
            }
          },*/
        ]
      },
      zoom: zoom,
      center: center,//定位的中心点
      attributionControl: false,
      // customAttribution: '©gips',//个性化标签
      // preserveDrawingBuffer: true,//允许canvas 转图片

      /*maxBounds: [[114.33703422546387,
        30.520938231971368], [114.37754631042479, 30.559600267673375
      ]],//*/
      fadeDuration: 0,//icon 的隐现效果
    });

    map.setMinZoom(zoomRange[0]);
    map.setMaxZoom(zoomRange[1]);

    //控件
    map.addControl(new mapboxgl.NavigationControl());
    //定位控件
    // Add geolocate control to the map.
     map.addControl(new mapboxgl.GeolocateControl({
       positionOptions: {
         enableHighAccuracy: true
       },
       trackUserLocation: true
     }));
    if (option.success) {
      option.success(map)
    }

    map.on('load', function () {

      if (option.onLoad) {
        option.onLoad(map)
      }

    });

    return map
  } else {
    throw 'RenderMap缺少参数：divId'
  }

}

export {RenderMap}
