package top.geomatics.ips.server.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.geomatics.ips.algorithms.PositioningResult;
import top.geomatics.ips.algorithms.base.BayesPositioning;
import top.geomatics.ips.algorithms.knn.NearestNeighborAlgorithm;

import top.geomatics.ips.model.wlan.WLANMeasurement;
import top.geomatics.ips.server.model.*;
import top.geomatics.ips.server.service.PositionServiceImpl;
import top.geomatics.ips.server.util.FileUtil;


import java.util.*;

/**
 * @author chenfa
 * 基于wifi的室内定位
 */
@Api(value = "/position", tags = "定位服务")
@RestController
@RequestMapping("/position")
public class PositionController{

    @Autowired
    public PositionServiceImpl positionService;

    @ApiOperation(value = "利用自定义算法", notes = "自定义算法")
    @PostMapping("/pos")
    public PositionResult pos(@RequestParam String json){
        return positionService.calculatePosition(json);
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
