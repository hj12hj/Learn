package com.hj.entity;

/**
 * @author: hj
 * @date: 2022/11/15
 * @time: 10:07 AM
 */
public class Message {

    public Message(String body) {
        this.body = body;
        this.header = body.getBytes().length;
    }

    private int header;
    private String  body;

    public int getHeader() {
        return header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
