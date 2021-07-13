package com.dbly.ssm.entity;

/**
 * 实体类
 * 属性       字段
 * 对象       记录
 * @author wxl
 */
public class User {
    private Integer id;
    private String username;
    private String pwd;
    private String phone;
    private String email;
    private Integer author;

    public User(Integer id, String username, String pwd, String phone, String email, Integer author) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.phone = phone;
        this.email = email;
        this.author = author;
    }

    public User(){
        super();
    }

    public Integer getAuthor() {
        return author;
    }



    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    public User setAuthor(Integer author) {
        this.author = author;
        return this;
    }
}
