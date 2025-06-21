package org.kob.kob.controller.user.bot;

import org.kob.kob.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddController {

    @Autowired
    private AddService addService;

    /**
     * 接收用户添加 Bot 的请求，将数据传递给 Service 层处理。
     * 
     * @param data 包含添加 Bot 所需信息的键值对
     * @return   返回操作结果，包含状态信息
     */
    @PostMapping("/user/bot/add/")
    public Map<String, String> add(@RequestParam Map<String, String> data) {
        return addService.add(data);
    }
}
