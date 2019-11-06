package top.geomatics.ips.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(value = "/admin", tags = "后台管理服务")
@Controller
public class AdminController {

    @ApiOperation(value = "后台管理首页", notes = "后台管理首页")
    @GetMapping("/admin")
    public String admin(){
        return "/admin";
    }
}
