package com.example.springbootplug;

import com.hj.Plugs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SpringBootApplication
public class SpringbootPlugApplication {


    @Bean("plugsList")
    public List<Plugs> plugsList() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Plugs> plugsList = new ArrayList<>();
//        ClassLoader classLoader = SpringbootPlugApplication.class.getClassLoader();
//        URL res = classLoader.getResource("plug");
//        if (res==null) return plugsList;
//        File res = new File("plug");
//        String path = res.getAbsolutePath();
        System.out.println(111111);
        System.out.println(111111);
        System.out.println(111111);

        File dirFile = new File("/Users/hejie/Desktop/java_learn/plug");
        System.out.println(dirFile.getAbsolutePath());
        if (!dirFile.exists()) return plugsList;
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

        return plugsList;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPlugApplication.class, args);
    }

}
