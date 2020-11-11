package com.example.jira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : XXXX
 * Author by zengh17
 * Date on 2020/11/11 11:38
 */

@RestController
public class DemoController {

    @RequestMapping("/")
    public String index(){
        return "nihao";
    }
}
