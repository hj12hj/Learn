package com.hj;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hj
 * @date: 2022/11/16
 * @time: 2:50 PM
 */
public class FreeMarkerApp {
    public static void main(String[] args) throws Exception {

        User root  = new User();
        root.setName("root");


        User user1 = new User();
        user1.setName("user1");

        User user2 = new User();
        user2.setName("user2");


        root.getChildren().add(user1);
        root.getChildren().add(user2);

        Map map=new HashMap();
        map.put("root", root);



        String path = "/Users/hejie/Desktop/java_learn/log4j2/log4j2/src/main/resources";


        //1.创建配置类
        Configuration configuration=new Configuration(Configuration.getVersion());
//2.设置模板所在的目录
        configuration.setDirectoryForTemplateLoading(new File(path));
//3.设置字符集
        configuration.setDefaultEncoding("utf-8");
//4.加载模板
        Template template = configuration.getTemplate("hello.ftl");
//5.创建数据模型

//6.创建Writer对象
        Writer out =new FileWriter(new File(path+"/hello.html"));
//7.输出
        template.process(map, out);
//8.关闭Writer对象
        out.close();

    }


}

class User{
    String name;
    List<User> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getChildren() {
        return children;
    }

    public void setChildren(List<User> children) {
        this.children = children;
    }
}
