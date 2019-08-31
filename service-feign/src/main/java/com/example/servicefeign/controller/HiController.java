package com.example.servicefeign.controller;

import com.example.servicefeign.dao.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")
    public String sayHi (@RequestParam(name = "name",defaultValue = "wahaha") String name){
        return schedualServiceHi.sayHiFromClientOne( name );
    }
}
