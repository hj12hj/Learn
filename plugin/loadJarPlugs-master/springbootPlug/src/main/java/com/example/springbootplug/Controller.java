package com.example.springbootplug;

import com.hj.Plugs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hj
 * @date: 2022/10/26
 * @time: 2:50 PM
 */
@RestController
public class Controller {


    @Autowired
    private List<Plugs> plugsList;


    @GetMapping("/test")
    public String test(){
        plugsList.forEach(plugs -> plugs.show());
        return "test";
    }

}
