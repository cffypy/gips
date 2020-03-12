package top.geomatics.ips.server.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.geomatics.ips.algorithms.coordinate.Convert;

@Api(value = "/utils", tags = "工具计算服务")
@RestController
public class UtilsControlller {
    @ApiOperation(value = "计算第一偏心率", notes = "计算第一偏心率")
    @PostMapping(value = "/getE1")
    public double getE1(@RequestParam @ApiParam(value="长半轴") double a,
                        @RequestParam @ApiParam(value="短半轴")double b){
        return Convert.getE1(a, b);
    }

    @ApiOperation(value = "计算第二偏心率", notes = "计算第二偏心率")
    @PostMapping(value = "/getE2")
    public double getE2(@RequestParam @ApiParam(value="长半轴") double a,
                        @RequestParam @ApiParam(value="短半轴")double b){
        return Convert.getE2(a, b);
    }

    @ApiOperation(value = "计算某一点的卯酉圈曲率半径", notes = "计算某一点的卯酉圈曲率半径")
    @PostMapping(value = "/getN")
    public double getN(@RequestParam @ApiParam(value="长半轴") double a,
                       @RequestParam @ApiParam(value="短半轴")double b,
                       @RequestParam @ApiParam(value="大地纬度")double B[]){
        return Convert.getN(a, b, B);
    }

    @ApiOperation(value = "度分秒转换为度", notes = "度分秒转换为度")
    @PostMapping(value = "/getDegree")
    public double getDegree(@RequestParam double deg, @RequestParam double min, @RequestParam double sec){
        return Convert.convertCoordinate(deg, min, sec);
    }

    @ApiOperation(value = "度转换为度分秒", notes = "度转换为度分秒")
    @PostMapping(value = "/getDFM")
    public String getDFM(@RequestParam double deg){
        return Convert.convert2DFM(deg);
    }

    @ApiOperation(value = "根据坐标系统获取椭球体参数", notes = "根据坐标系统获取椭球体参数")
    @PostMapping(value = "/getdata")
    public double[] getdata(@RequestParam @ApiParam(value="坐标系统(1-北京54,2-西安80,3-WGS84,4-克拉索夫斯基)")int system){
        return Convert.getdata(system);
    }

}
