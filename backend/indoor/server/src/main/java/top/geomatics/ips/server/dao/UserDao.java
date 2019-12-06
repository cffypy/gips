package top.geomatics.ips.server.dao;

import top.geomatics.ips.server.model.User;

public interface UserDao {
    //用户注册
    public String register(User user);
    //用户登录
    public String login(String name,String password);
    //用户退出
    public String logout(String username);
    //修改密码
    public String reset(String username);
    //获取用户个人信息
    public User userinfo(String username);
}
