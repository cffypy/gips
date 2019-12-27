<template xmlns:v-bind="http://www.w3.org/1999/xhtml">

  <div class='baseMapWrapper' ref="baseMapWrapper">
    <div id="baseMap" class="baseMap"></div>
    <!--地图切换按钮-->
    <div @click="changeMap" class="mapToggle">{{mapName}}</div>
    <!--<Spin  fix v-if="showLoad"></Spin>-->
    <!--楼层切换按钮-->
    <div class="floorToggle flex_col mapboxgl-ctrl-group" v-if="showFloors" >
      <div v-for="(f,i) in floors" :class="curFloor==i?'item curFloor':'item'" @click="toggleFloor(i)">{{f}}</div>
    </div>
  </div>

</template>


<script>
  import {RenderMap} from '../js/mapRender'
  export default {
    name: 'baseMap',
    data () {
      return {
        showLoad:true,
        mapName:'高德',
        floors:[
          '1F','2F','3F','4F','5F'
        ],
        showFloors:false,
        curFloor:0
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
         /* center: [ 114.35476899147034,
            30.529440764451593],*/
          center:[ 114.52629,
            30.4698],
          zoom: 19,
          zoomRange:[1,22],
          onLoad (map) {
           // $this.showLoad = false;
            showFloorByZoom(map);
            map.on('zoomend',function (e) {
              showFloorByZoom(map);
            });
            /*map.on('click','fill-indoor',function (e) {
              debugger
            })*/
          },
          success (map) {
            $this.map = map

          }
        });

        function showFloorByZoom(map) {
          let zoom = map.getZoom();
          if(zoom>=17){
            $this.showFloors = true;
            map.setPitch(45)
          }else{
            $this.showFloors = false;
            map.setPitch(0)
          }
        }
      },
      //获取当前手机位置
      getLocation () {
        var map = this.map;
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function (position) {
              var lat = position.coords.latitude,
                lng = position.coords.longitude;
              map.getSource('locationPoint').setData({
                'type': 'FeatureCollection',
                'features': [{
                  'type': 'Feature',
                  'geometry': {
                    'type': 'Point',
                    'coordinates': [lng, lat]
                  }
                }]
              });
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
      //切换地图底图
      changeMap(){
        switch (this.mapName) {
          case '高德':
            this.mapName = 'OSM';
            this.map.setLayoutProperty('osm-layer','visibility','visible');
            this.map.setLayoutProperty('gMap-layer','visibility','none');
            break;
          case 'OSM':
            this.mapName = '高德';
            this.map.setLayoutProperty('gMap-layer','visibility','visible');
            this.map.setLayoutProperty('osm-layer','visibility','none');
            break;
        }
      },
      //切换楼层
      toggleFloor(floorIndex){
        let $this = this;
        $this.curFloor = floorIndex;
        $this.map.setFilter('border-indoor',['==','floor',floorIndex+1]);
        $this.map.setFilter('fill-indoor',['==','floor',floorIndex+1]);
      }
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

  .mapToggle{
    position: absolute;
    bottom: 10px;
    right: 10px;
    color: #fff;
    background: #356AFB;
    width: 40px;
    height: 40px;
    vertical-align: middle;
    text-align: center;
    line-height: 40px;
    border-radius: 10px;
    cursor: pointer;
  }
  .floorToggle{
    position: absolute;
    bottom: 10px;
    left: 10px;
    box-shadow: 0 0 0 2px rgba(0, 0, 0, 0.1);
  }
  .floorToggle .item{
    border-bottom:1px solid rgba(0, 0, 0, 0.2);
    padding: 0.7rem;
    cursor: pointer;
  }
  .floorToggle .item:last-child{
    border-bottom: none;
  }
  .floorToggle .curFloor{
    color: #356AFB;
    border: 1px solid  #356AFB !important;
  }
</style>
