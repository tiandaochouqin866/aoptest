package com;

import com.aop.biz.BizA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangshuaibiao
 * @Date: 2018/6/12 16:57
 */
@SpringBootApplication
@RestController
public class MainApplicaiton {

    @Autowired
    BizA bizA;

    @GetMapping("/")
    public String test() {
        bizA.doSomething();
        return "ttt";
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplicaiton.class, args);
    }
}
