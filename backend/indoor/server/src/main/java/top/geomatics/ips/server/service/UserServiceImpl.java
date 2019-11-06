package top.geomatics.ips.server.service;

import org.springframework.stereotype.Service;
import top.geomatics.ips.server.model.User;

@Service
public class UserServiceImpl implements  UserService {
    @Override
    public String register(User user) {
        return null;
    }

    @Override
    public String login(String name,String password) {
        return null;
    }

    @Override
    public String logout(String username) {
        return null;
    }

    @Override
    public String reset(String username) {
        return null;
    }

    @Override
    public User userinfo(String username) {
        return null;
    }
}
