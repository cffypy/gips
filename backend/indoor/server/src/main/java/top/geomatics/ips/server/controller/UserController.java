package top.geomatics.ips.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;
import top.geomatics.ips.server.model.User;
import top.geomatics.ips.server.service.UserServiceImpl;

@Api(value = "/user", tags = "用户服务")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserServiceImpl userService;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public String register(@RequestParam String json){
        User user = JSON.parseObject(json, User.class);
        userService.register(user);
        return "register";
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){
        userService.login(username, password);
        return "login";
    }

    @ApiOperation(value = "用户注销", notes = "用户注销")
    @GetMapping("/logout")
    public String logout(@RequestParam String username){
        userService.logout(username);
        return "logout";
    }

    @ApiOperation(value = "用户修改密码", notes = "用户修改密码")
    @GetMapping("/reset")
    public String reset(@RequestParam String username){
        userService.reset(username);
        return "reset";
    }

    @ApiOperation(value = "用户修改密码", notes = "用户修改密码")
    @GetMapping("/userinfo")
    public String userinfo(@RequestParam String username){
        userService.userinfo(username);
        return "userinfo";
    }
}
