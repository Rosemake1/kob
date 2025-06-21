package org.kob.kob.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.kob.kob.mapper.BotMapper;
import org.kob.kob.pojo.Bot;
import org.kob.kob.pojo.User;
import org.kob.kob.service.impl.utils.UserDetailsImpl;
import org.kob.kob.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private BotMapper botMapper;
    @Override
    public List<Bot> getList() {
        UsernamePasswordAuthenticationToken authenticationToken =
        (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext
            ().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        // 创建查询条件，根据当前登录用户的ID查询对应的Bot列表
        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId()); // 构造查询条件：user_id等于当前用户ID
        return botMapper.selectList(queryWrapper); // 执行查询并返回结果列表
    }
}
