package com.example.esdemo.controller;


import com.example.esdemo.service.FileParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Testcontroller {


    @Autowired
    FileParsingService fileParsingService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public void importTest() {
        fileParsingService.fun();
    }


}
