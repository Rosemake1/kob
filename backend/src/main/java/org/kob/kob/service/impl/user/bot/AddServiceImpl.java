package org.kob.kob.service.impl.user.bot;


import org.kob.kob.mapper.BotMapper;
import org.kob.kob.pojo.Bot;
import org.kob.kob.pojo.User;
import org.kob.kob.service.impl.utils.UserDetailsImpl;
import org.kob.kob.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {
    @Autowired
    private BotMapper botMapper;
    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");

        Map<String, String> map = new HashMap<>();
        if(title == null || title.isEmpty()){
            map.put("error_message","标题不能为空");
            return map;
        }
        if(title.length() > 100){
            map.put("error_message","标题长度不能超过100");
            return map;
        }
        if(description == null || description.isEmpty()){
            map.put("error_message","这个用户不会写");
        }
        if(description.length() > 300){
            map.put("error_message","标题长度不能超过300");
            return map;
        }
        if (content == null || content.isEmpty()) {
            map.put("error_message", "代码不能为空");
            return map;
        }
        if(content.length()>10000)
        {
            map.put("error_message","代码长度不能超过10000");
            return map;
        }
        Date now = new Date();
        Bot bot= new Bot(null,user.getId(),title,description,content,"0",now,now);
        // 将新创建的 Bot 对象插入数据库
        botMapper.insert(bot);
        // 返回成功消息
        map.put("error_message", "success");
        return map;
    }
}
