package com.hj;

import com.hj.base.IMapper;
import com.hj.base.IMapperImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MethodInvoker;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@SpringBootApplication
public class ShiroApplication {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SpringApplication.run(ShiroApplication.class, args);

    }


}
