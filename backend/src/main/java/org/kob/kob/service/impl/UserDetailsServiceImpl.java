package org.kob.kob.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.kob.kob.mapper.UserMapper;
import org.kob.kob.pojo.User;
import org.kob.kob.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null)
        {
            throw new RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
