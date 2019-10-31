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
import top.geomatics.ips.server.entity.*;
import top.geomatics.ips.server.service.PositionServiceImpl;
import top.geomatics.ips.server.util.FileUtil;


import java.util.*;

/**
 * @author chenfa
 * 基于wifi的室内定位
 */

@Api(value = "/position", tags = "Wifi定位服务")
@RestController
@RequestMapping("/position")
public class PositionController{

    @Autowired
    public PositionServiceImpl positionService;

    @ApiOperation(value = "利用cf算法", notes = "cf算法")
    @PostMapping("/cf")
    public PositionResult cf(@RequestParam String json){
        return positionService.calculatePosition(json);
    }

    @ApiOperation(value = "利用knn算法", notes = "knn算法")
    @PostMapping("/knn")
    public PositioningResult knn(@RequestBody WLANMeasurement measurements ){
        NearestNeighborAlgorithm knn=new NearestNeighborAlgorithm();
        PositioningResult positioningResult=knn.calculatePosition(measurements);
        return positioningResult;
    }

    @ApiOperation(value = "测试", notes = "测试")
    @PostMapping("/test")
    public String test(@RequestBody  @ApiParam(name = "请求对象", value = "传入json格式", required = true)Test test ){
        //@RequestBody注解是将传过来的json数据直接转换成JavaBean对象
        System.out.println(test.getName());
        return test.getName();
    }

    @ApiOperation(value = "利用bayes算法", notes = "bayes算法")
    @PostMapping("/bayes")
    public PositioningResult bayes(@RequestBody WLANMeasurement measurements ){
        BayesPositioning bayes=new BayesPositioning();
        PositioningResult positioningResult=bayes.calculatePosition(measurements);
        return positioningResult;
    }

    @ApiOperation(value = "读取android手机提交的wifi数据", notes = "读取android手机提交的wifi数据")
    @PostMapping("/android")
    public void android(@RequestParam String json){
        //将json转化为java对象
        List<ScanInfo> scaninfos = JSON.parseArray(json, ScanInfo.class);
        for(int i=0;i<scaninfos.size();i++){
            String mac=scaninfos.get(i).getSSID();//得到mac地址
            Double level=scaninfos.get(i).getLevel();//获取信号强度
            Map<String,Double> map=new LinkedHashMap<>();//LinkedHashMap是有序的
            map.put(mac,level);
        }
    }

    @ApiOperation(value = "读取柳老师组提供的wifi数据", notes = "读取柳老师组提供的wifi数据")
    @PostMapping("/hipe")
    public void hipe(@RequestParam String filepath){
        //String filepath="C:\\Users\\chenfa\\Desktop\\HIPE\\WifiFPDB.json";
        String jsonContent= FileUtil.ReadFile(filepath);
        //将json转化为java对象
        List<FP_position> fp_positions = JSON.parseArray(jsonContent, FP_position.class);
        List<FP_info> fp_infos = JSON.parseArray(jsonContent, FP_info.class);

//        List<Map<String,Double>> macdata=new ArrayList<Map<String,Double>>();
        List<String> macdata=new ArrayList<String>();
        for(int i=0;i<fp_positions.size();i++){
            fp_infos=fp_positions.get(i).getFP_info();
            for(int j=0;j<fp_infos.size();j++){
                String mac=fp_infos.get(j).getMAC();
                double rssi=fp_infos.get(j).getMeanRssi();
//                String mac1=fp_positions.get(0).getFP_info().get(1).getMAC();
//                System.out.println(mac);
                macdata.add(mac);
            }
        }
        System.out.println(macdata);

    }

}
