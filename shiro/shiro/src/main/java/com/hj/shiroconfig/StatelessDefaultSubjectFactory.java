package com.hj.shiroconfig;

/**
 * @author: hj
 * @date: 2022/11/17
 * @time: 5:10 下午
 */
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * <p>Version: 1.0
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(true);
        context.setAuthenticated(true);
        return super.createSubject(context);
    }
}