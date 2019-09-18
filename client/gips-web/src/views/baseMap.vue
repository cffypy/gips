<template xmlns:v-bind="http://www.w3.org/1999/xhtml">

  <div class='baseMapWrapper' ref="baseMapWrapper">
    <div id="baseMap" class="baseMap"></div>
    <!--点击定位到当前-->
   <!-- <div class="mapboxgl-ctrl mapboxgl-ctrl-group posBox">
      <button class="mapboxgl-ctrl-icon mapboxgl-ctrl-geolocate" @click="getLocation"
              type="button"
      ></button>

    </div>-->
    <Spin size="large" fix v-if="showLoad"></Spin>
  </div>

</template>


<script>
  import {RenderMap} from '../js/mapRender'

  export default {
    name: 'baseMap',
    data () {
      return {
        showLoad:true,
      }
    },
    components: {},
    computed: {
    },
    props: {},
    created () {

    },
    mounted () {
      this.init();
    },
    methods: {
      //地图初始化d
      init () {
        let $this = this;
        RenderMap('baseMap', {
          center: [114.36076641082762,
            30.541084329869697],
          zoom: 10,
          onLoad (map) {

           /* map.addSource('locationPoint', {
              'type': 'geojson',
              data: {
                'type': 'FeatureCollection',
                'features': [
                  {'type': 'Feature', 'geometry': {'type': 'Point', 'coordinates': [114.30, 30.52]}}
                ]
              }
            })
            //symbol图层
            map.addLayer({
              'id': 'locationBG',
              'type': 'circle',
              'source': 'locationPoint',
              'layout': {},
              'paint': {
                'circle-color': '#52CAF5',
                'circle-radius': [
                  'interpolate', ['linear'], ['zoom'],
                  12, 10,
                  13, 20,
                  14, 30,
                  15, 40,
                  16, 50,
                  17, 60,
                  18, 70,
                ],
                'circle-opacity': 0.3,
                'circle-stroke-width': 1,
                'circle-stroke-color': '#52CAF5'
              }
            })
            map.addLayer({
              'id': 'location',
              'type': 'circle',
              'source': 'locationPoint',
              'layout': {},
              'paint': {
                'circle-color': '#52CAF5',
                'circle-radius': 5
              }
            })

            $this.getLocation();
            */
           $this.showLoad = false;
          },
          success (map) {
            $this.map = map

          }
        })
      },
      //获取当前手机位置
      getLocation () {
        var map = this.map
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function (position) {
              var lat = position.coords.latitude,
                lng = position.coords.longitude
              map.getSource('locationPoint').setData({
                'type': 'FeatureCollection',
                'features': [{
                  'type': 'Feature',
                  'geometry': {
                    'type': 'Point',
                    'coordinates': [lng, lat]
                  }
                }]
              })
              map.flyTo({
                center: [lng, lat],
                zoom: 16
              })

            }, function (err) {
              console.log('Geolocation is not supported by this browser.')
            },
            {
              enableHighAccuracy: true,//是否要求高精度的地理位置信息
              timeout: 1000,//对地理位置信息的获取操作做超时限制，如果再该事件内未获取到地理位置信息，将返回错误
              maximumAge: 60 * 1000//设置缓存有效时间，在该时间段内，获取的地理位置信息还是设置此时间段之前的那次获得的信息，超过这段时间缓存的位置信息会被废弃
            })
        } else {
          console.log('Geolocation is not supported by this browser.')
        }
      },

    },
    watch: {},
    beforeDestroy (to, from, next) {
      if (this.map) {
        this.map.remove()
      }

    }
  }
</script>

<style scoped>
  .baseMapWrapper {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    overflow: hidden;
  }

  .baseMapWrapper .baseMap {
    text-align: left;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    position: absolute;
    /*transform:scale(1.412);*/
  }

  .posBox {
    position: absolute;
    top: 110px;
    right: 10px;
  }

</style>
