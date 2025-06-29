package org.kob.kob.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.kob.kob.mapper.UserMapper;
import org.kob.kob.pojo.User;
import org.kob.kob.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl  implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> register(String username, String password, String ConfirmedPassword) {
        Map<String,String> map = new HashMap<>();
        if(username == null){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password == null){
            map.put("error_message","密码不能为空");
            return map;
        }
        username = username.trim();
        if(username.isEmpty()){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password.isEmpty() || ConfirmedPassword.isEmpty()){
            map.put("error_message","密码不能为空");
            return map;
        }
        if(username.length() > 100){
            map.put("error_message","用户名长度不能大于100");
            return map;
        }
        if(password.length() > 100 || ConfirmedPassword.length() > 100){
            map.put("error_message","密码长度不能大于100");
            return map;
        }
        if(!password.equals(ConfirmedPassword))
        {
            map.put("error_message","两次密码不一致");
            return map;
        }
                                                                                                                                                                                                                                                                                                                                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty())
        {
            map.put("error_message","用户名已存在");
            return map;
        }
        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/389996_lg_e72617d794.jpg";
        User user = new User(null,username,encodedPassword,photo);
        userMapper.insert(user);
        map.put("error_message","success");
        return map;
    }
}














