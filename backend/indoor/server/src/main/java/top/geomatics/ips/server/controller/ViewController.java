package top.geomatics.ips.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(value = "/file", tags = "视图服务")
@Controller
public class ViewController {

    @ApiOperation(value = "获取文件上传页面", notes = "获取文件上传页面")
    @GetMapping("/file")
    public String file(){
        return "/file";
    }

    @ApiOperation(value = "获取下载页面", notes = "获取下载页面")
    @GetMapping("/download1")
    public String download(){
        return "/download";
    }

    @ApiOperation(value = "定位结果", notes = "定位结果")
    @GetMapping("/result")
    public String result(){
        return "/result";
    }
}
