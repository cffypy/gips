package top.geomatics.ips.server.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.geomatics.ips.server.model.Test;


@RestController
public class TestController {
    @ApiOperation(value = "测试", notes = "测试")
    @PostMapping("/test")
    public String test(@RequestBody @ApiParam(name = "请求对象", value = "传入json格式", required = true) Test test ){
        //@RequestBody注解是将传过来的json数据直接转换成JavaBean对象
        System.out.println(test.getName());
        return test.getName();
    }
}
