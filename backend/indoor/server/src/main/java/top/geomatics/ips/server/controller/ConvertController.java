package top.geomatics.ips.server.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.geomatics.ips.algorithms.coordinate.Convert;
import top.geomatics.ips.algorithms.coordinate.ConvertAlgorithm;

@Api(value = "/convert", tags = "坐标转换服务")
@RestController
public class ConvertController {
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
    @PostMapping(value = "/getDu")
    public double getDu(@RequestParam double deg, @RequestParam double min, @RequestParam double sec){
        return Convert.convertCoordinate(deg, min, sec);
    }

    @ApiOperation(value = "大地坐标转三维空间直角坐标", notes = "大地坐标转三维空间直角坐标")
    @PostMapping(value = "/D2T")
    public double[] D2T(@RequestParam @ApiParam(value="长半轴") double a,
                        @RequestParam @ApiParam(value="短半轴")double b,
                        @RequestParam @ApiParam(value="大地纬度(输入长度为3的数组，依次为度分秒)")double B[],
                        @RequestParam @ApiParam(value="大地纬度(输入长度为3的数组，依次为度分秒)")double L[],
                        @RequestParam @ApiParam(value="大地高")double H){
        double n=Convert.getN(a,b,B);
        return ConvertAlgorithm.D2T(a,b,n,B,L,H);
    }

    @ApiOperation(value = "三维空间直角坐标装大地坐标", notes = "三维空间直角坐标装大地坐标")
    @PostMapping(value = "/T2D")
    public double[] T2D(@RequestParam @ApiParam(value="长半轴") double a,
                        @RequestParam @ApiParam(value="短半轴")double b,
                        @RequestParam @ApiParam(value="X")double X,
                        @RequestParam @ApiParam(value="Y")double Y,
                        @RequestParam @ApiParam(value="Z")double Z){
        return ConvertAlgorithm.T2D(a,b,X,Y,Z);
    }

    @ApiOperation(value = "大地坐标转高斯坐标", notes = "大地坐标转高斯坐标")
    @PostMapping(value = "/D2G")
    public double[] D2G(@RequestParam double a,
                        @RequestParam double b,
                        @RequestParam double B[],
                        @RequestParam double L[],
                        @RequestParam int dai,
                        @RequestParam int system){
        return ConvertAlgorithm.D2G(a, b, dai, B, L, system);
    }

    @ApiOperation(value = "高斯坐标转大地坐标", notes = "高斯坐标转大地坐标")
    @PostMapping(value = "/G2D")
    public double[] G2D(@RequestParam double a,
                        @RequestParam double b,
                        @RequestParam double X,
                        @RequestParam double Y,
                        @RequestParam int dai,
                        @RequestParam int system){
        return ConvertAlgorithm.G2D(a, b, dai, X, Y, system);
    }
}
