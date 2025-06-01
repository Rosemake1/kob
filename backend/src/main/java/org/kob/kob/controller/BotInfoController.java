package org.kob.kob.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/pk/")
public class BotInfoController {
    @RequestMapping ("getbotinfo/")
    public List<Map<String, String>> getBotInfo(){
        List <Map<String, String>> res = new LinkedList<>();
        Map<String,String> name1 = new HashMap<>();
         name1.put("name","小猫");
         name1.put("like","cute");
        Map <String,String> name2 = new HashMap<>();
        name2.put("name","nkz");
        name2.put("like","帅");
        res.add( name1);
         res.add( name2);
         return res;
    }
}
