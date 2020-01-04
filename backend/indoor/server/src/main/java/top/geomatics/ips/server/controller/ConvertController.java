package top.geomatics.ips.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.geomatics.ips.algorithms.coordinate.Convert;

@Api(value = "/convert", tags = "坐标转换服务")
@RestController
public class ConvertController {
    @ApiOperation(value = "计算偏心率", notes = "计算偏心率")
    @PostMapping(value = "/getE")
    public double getE(@RequestParam double a, @RequestParam double b){
        return Convert.getE(a, b);
    }

    @ApiOperation(value = "计算某一点的卯酉圈曲率半径", notes = "计算某一点的卯酉圈曲率半径")
    @PostMapping(value = "/getN")
    public double getN(@RequestParam double a, @RequestParam double b, @RequestParam double B[]){
        return Convert.getN(a, b, B);
    }

    @ApiOperation(value = "度分秒转换为度", notes = "度分秒转换为度")
    @PostMapping(value = "/getDu")
    public double getDu(@RequestParam double deg, @RequestParam double min, @RequestParam double sec){
        return Convert.ConvertCoordinate(deg, min, sec);
    }

    @ApiOperation(value = "大地坐标求三维直角坐标", notes = "大地坐标求三维直角坐标")
    @PostMapping(value = "/c1")
    public double[] c1(@RequestParam double a, @RequestParam double b,
                       @RequestParam double B[], @RequestParam double L[], @RequestParam double H){
        double n=Convert.getN(a,b,B);
        return Convert.XYZ(a,b,n,B,L,H);
    }
}
