package com.tao.dubbo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangantao on 2017/4/13.
 */
public class UserInfo implements Serializable {
    private  Long id;
    private  String userName;
    private  String pwd;
    private  String address;
    private  String phone;
    private  Date   regTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
