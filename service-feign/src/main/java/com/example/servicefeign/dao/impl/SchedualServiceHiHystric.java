package com.example.servicefeign.dao.impl;

import com.example.servicefeign.dao.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name+", 服务器开小差了!";
    }
}
