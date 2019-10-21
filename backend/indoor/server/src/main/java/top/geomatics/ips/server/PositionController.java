package top.geomatics.ips.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.geomatics.ips.algorithms.PositioningResult;
import top.geomatics.ips.algorithms.base.BayesPositioning;
import top.geomatics.ips.algorithms.knn.NearestNeighborAlgorithm;
import top.geomatics.ips.model.wlan.WLANMeasurement;
import top.geomatics.ips.server.util.Test;

/**
 * @author chenfa
 * 基于wifi的室内定位
 */

@Api(value = "/position", tags = "WIFI定位服务")
@RestController
@RequestMapping("/position")
public class PositionController{

    @ApiOperation(value = "测试", notes = "测试")
    @PostMapping("/test")
    public String test(@RequestBody  @ApiParam(name = "请求对象", value = "传入json格式", required = true)Test test ){
        //@RequestBody注解是将传过来的json数据直接转换成JavaBean对象
        System.out.println(test.getName());
        return test.getName();
    }

    @ApiOperation(value = "利用knn算法", notes = "knn算法")
    @PostMapping("/knn")
    public PositioningResult knn(@RequestBody WLANMeasurement measurements ){
        NearestNeighborAlgorithm knn=new NearestNeighborAlgorithm();
        PositioningResult positioningResult=knn.calculatePosition(measurements);
        return positioningResult;
    }

    @ApiOperation(value = "利用bayes算法", notes = "bayes算法")
    @PostMapping("/bayes")
    public PositioningResult bayes(@RequestBody WLANMeasurement measurements ){
        BayesPositioning bayes=new BayesPositioning();
        PositioningResult positioningResult=bayes.calculatePosition(measurements);
        return positioningResult;
    }

}
