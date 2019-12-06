package top.geomatics.ips.server.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.geomatics.ips.server.model.FP_info;
import top.geomatics.ips.server.model.FP_position;
import top.geomatics.ips.server.model.ScanInfo;
import top.geomatics.ips.server.service.FileServiceImpl;
import top.geomatics.ips.server.util.Csv2json;
import top.geomatics.ips.server.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenfa
 * 数据服务
 */
@Api(value = "/data", tags = "数据服务")
@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    public FileServiceImpl fileService;

    @ApiOperation(value = "保存wifi信息", notes = "保存wifi信息")
    @PostMapping("/mysql")
    @ResponseBody
    public void mysql(@RequestBody ScanInfo scaninfo){

    }

    @ApiOperation(value = "读取json文件", notes = "读取json文件")
    @PostMapping("/readjson")
    public String readjson(@RequestParam String filePath)  {
        String json = FileUtil.ReadFile(filePath);
        return json;
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

    @ApiOperation(value = "csv转json", notes = "csv转json")
    @PostMapping("/csv2json")
    public void csv2json(@RequestParam String filePath,@RequestParam String outPutPath) throws Exception {
        Csv2json csv2json = new Csv2json();
        csv2json.ConvertToJson(filePath, outPutPath);
    }






}
