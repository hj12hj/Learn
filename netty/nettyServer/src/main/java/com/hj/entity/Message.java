package com.hj.entity;

/**
 * @author: hj
 * @date: 2022/11/15
 * @time: 10:07 AM
 */
public class Message {
    private int header;
    private String  body;
    public Message(String body) {
        this.body = body;
        this.header = body.getBytes().length;
    }
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

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                ", body='" + body + '\'' +
                '}';
    }
}
