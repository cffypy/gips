package top.geomatics.ips.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.geomatics.ips.algorithms.coordinate.Convert;
import top.geomatics.ips.algorithms.coordinate.ConvertAlgorithm;
import java.util.ArrayList;
import java.util.List;

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
    @PostMapping(value = "/getDegree")
    public double getDegree(@RequestParam double deg, @RequestParam double min, @RequestParam double sec){
        return Convert.convertCoordinate(deg, min, sec);
    }

    @ApiOperation(value = "度转换为度分秒", notes = "度转换为度分秒")
    @PostMapping(value = "/getDFM")
    public String getDFM(@RequestParam double deg){
        return Convert.convert2DFM(deg);
    }

    @ApiOperation(value = "大地坐标转三维空间直角坐标(单点转换)", notes = "大地坐标转三维空间直角坐标(单点转换)")
    @PostMapping(value = "/D2T")
    public double[] D2T(@RequestParam @ApiParam(value="长半轴") double a,
                        @RequestParam @ApiParam(value="短半轴")double b,
                        @RequestParam @ApiParam(value="大地纬度(输入长度为3的数组，依次为度分秒)")double B[],
                        @RequestParam @ApiParam(value="大地经度(输入长度为3的数组，依次为度分秒)")double L[],
                        @RequestParam @ApiParam(value="大地高")double H){
        double N=Convert.getN(a,b,B);
        return ConvertAlgorithm.D2T(a,b,N,B,L,H);
    }

    @ApiOperation(value = "三维空间直角坐标转大地坐标(单点转换)", notes = "三维空间直角坐标转大地坐标(单点转换)")
    @PostMapping(value = "/T2D")
    public List<Object> T2D(@RequestParam @ApiParam(value="长半轴" ) double a,
                        @RequestParam @ApiParam(value="短半轴")double b,
                        @RequestParam @ApiParam(value="空间直角坐标X")double X,
                        @RequestParam @ApiParam(value="空间直角坐标Y")double Y,
                        @RequestParam @ApiParam(value="空间直角坐标Z")double Z){
        double[] result= ConvertAlgorithm.T2D(a,b,X,Y,Z);
        String bb=Convert.convert2DFM(result[0]);
        String ll=Convert.convert2DFM(result[1]);
        double hh=result[2];
        List<Object> list=new ArrayList();
        list.add(bb);
        list.add(ll);
        list.add(hh);
        return list;
    }

    @ApiOperation(value = "大地坐标转高斯坐标(单点转换)", notes = "大地坐标转高斯坐标(单点转换)")
    @PostMapping(value = "/D2G")
    public double[] D2G(@RequestParam @ApiParam(value="长半轴") double a,
                        @RequestParam @ApiParam(value="短半轴")double b,
                        @RequestParam @ApiParam(value="大地纬度(输入长度为3的数组，依次为度分秒)")double B[],
                        @RequestParam @ApiParam(value="大地经度(输入长度为3的数组，依次为度分秒)")double L[],
                        @RequestParam @ApiParam(value="分带")int dai){
        return  ConvertAlgorithm.D2G(a, b, dai, B, L);
    }

    @ApiOperation(value = "高斯坐标转大地坐标(单点转换)", notes = "高斯坐标转大地坐标(单点转换)")
    @PostMapping(value = "/G2D")
    public double[] G2D(@RequestParam @ApiParam(value="长半轴") double a,
                        @RequestParam @ApiParam(value="短半轴")double b,
                        @RequestParam @ApiParam(value="高斯坐标X")double X,
                        @RequestParam @ApiParam(value="高斯坐标Y")double Y,
                        @RequestParam @ApiParam(value="分带")int dai){
        return ConvertAlgorithm.G2D(a, b, dai, X, Y);
    }

    @ApiOperation(value = "大地转高斯", notes = "大地转高斯")
    @PostMapping(value = "/BLtoXY")
    public double[] BLtoXY(@RequestParam @ApiParam(value="大地纬度")double B,
                           @RequestParam @ApiParam(value="大地经度")double L){
        return ConvertAlgorithm.BLtoXY(L,B);
    }

    @ApiOperation(value = "高斯转大地", notes = "高斯转大地")
    @PostMapping(value = "/XYtoBL")
    public double[] XYtoBL(@RequestParam @ApiParam(value="高斯坐标X")double X,
                           @RequestParam @ApiParam(value="高斯坐标Y")double Y){
        return ConvertAlgorithm.XYtoBL(X,Y);
    }
}
