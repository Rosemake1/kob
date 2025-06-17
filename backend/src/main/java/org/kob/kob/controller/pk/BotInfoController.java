package org.kob.kob.controller.pk;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("/pk/")
public class  BotInfoController {
    @RequestMapping ("getbotinfo/")
    public Map<String, String>getBotInfo(){
        Map<String,String> name1 = new HashMap<>();
         name1.put("name","宁康庄");
         name1.put("rating","很厉害");

         return name1;
    }
}
