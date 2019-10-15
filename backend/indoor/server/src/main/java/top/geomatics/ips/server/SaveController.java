package top.geomatics.ips.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.geomatics.ips.model.cf.ScanInfo;

@Api(value = "/save", tags = "数据存储服务")
@RestController
@RequestMapping("/save")
public class SaveController {

    @ApiOperation(value = "保存wifi信息", notes = "保存wifi信息")
    @PostMapping("/mysql")
    public void mysql(@RequestBody ScanInfo scaninfo){

    }
}
