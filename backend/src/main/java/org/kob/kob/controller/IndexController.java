package org.kob.kob.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/pk/")
public class IndexController {
    @RequestMapping ("index/")
    public String index()
    {
        return "pk/index";
    }

}
