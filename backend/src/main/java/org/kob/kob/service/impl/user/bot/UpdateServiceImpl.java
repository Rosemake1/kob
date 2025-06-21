package org.kob.kob.service.impl.user.bot;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.kob.kob.mapper.BotMapper;
import org.kob.kob.pojo.Bot;
import org.kob.kob.pojo.User;
import org.kob.kob.service.impl.utils.UserDetailsImpl;
import org.kob.kob.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private BotMapper botMapper;
    @Override
    public Map<String, String> update(Map<String, String> data) {
    UsernamePasswordAuthenticationToken authenticationToken =
        (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        int bot_id = Integer.parseInt(data.get("bot_id"));
        String title = data.get("title");
        String desciption = data.get("description");
        String content = data.get("content");
        Map<String, String> map = new HashMap<>();

        if(title == null || title.length() == 0){
            map.put("error_message","标题不能为空");
            return map;
        }
        if(title.length()>100)
        {
            map.put("error_message","标题长度不能超过100");
            return map;
        }
        if(desciption == null || desciption.length() == 0){
            map.put("error_message","什么都没有");
        }
        if(desciption.length()>300)
        {
            map.put("error_message","描述长度不能超过300");
            return map;
        }
        if (content == null || content.length() == 0) {
            map.put("error_message", "代码不能为空");
            return map;
        }
        if (content.length() > 10000) {
            map.put("error_message", "代码长度不能超过10000");
            return map;
        }

        Bot bot = botMapper.selectById(bot_id);
        if(bot == null){
            map.put("error_message","Bot不存在或者被删除");
            return map;
        }
        if(!bot.getUserId().equals(user.getId()))
        {
            map.put("error_message","没有权限修改");
            return map;
        }
        Bot new_bot = new Bot(
                bot.getId(),
                user.getId(),
                title,
                desciption,
                content,
                bot.getRating(),
                bot.getCreatetime(),
                new Date()
        );
        botMapper.updateById(new_bot);

        map.put("error_message","success");
        return map;
    }
}
