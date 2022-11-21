package com.hj.shiroconfig;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hj
 * @date: 2022/11/21
 * @time: 8:54 AM
 */
public class MySubjectDAO implements SubjectDAO {
    private Map<String, Object> map = new HashMap<>();

    @Override
    public Subject save(Subject subject) {
        map.put(subject.getSession().getId().toString(), subject);
        return subject;
    }

    @Override
    public void delete(Subject subject) {
        map.remove(subject.getSession().getId().toString());
    }
}
