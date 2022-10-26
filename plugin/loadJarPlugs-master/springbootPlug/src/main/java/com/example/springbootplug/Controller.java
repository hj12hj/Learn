package com.example.springbootplug;

import com.hj.Plugs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

    @GetMapping("/reload")
    public String reload() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        plugsList.clear();
        File dirFile = new File("plug");
        System.out.println(dirFile.getAbsolutePath());
        if (!dirFile.exists()) return "Ok";
        File[] files = dirFile.listFiles();
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
            if (file.exists() && file.getName().endsWith(".jar")) {
                JarFile jarFile = new JarFile(file);
                URL url = new URL("file:" + file.getAbsolutePath());
                ClassLoader loader = new URLClassLoader(new URL[]{url});//自己定义的classLoader类，
                Enumeration<JarEntry> es = jarFile.entries();
                while (es.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) es.nextElement();
                    String name = jarEntry.getName();
                    if (name != null && name.endsWith(".class")) {//只解析了.class文件，没有解析里面的jar包
                        //默认去系统已经定义的路径查找对象，针对外部jar包不能用
                        Class<?> c = loader.loadClass(name.replace("/", ".").substring(0, name.length() - 6));
                        Plugs plugs = (Plugs) c.newInstance();
                        plugsList.add(plugs);
                    }
                }
            }

        }

        return "Ok";
    }
}
