package com.hj.controlller;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

/**
 * @author: hj
 * @date: 2022/11/17
 * @time: 9:07 AM
 */
@RestController
public class TestController {
    @GetMapping("/test")
//    @RequiresRoles("admin")
    public String test() {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("hj", "123"));
        return "test";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
