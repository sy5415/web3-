package com.atguigu.bean;

/**
 * 包名:com.atguigu.bean
 *
 * @author Leevi
 * 日期2021-05-13  11:14
 */
public class User {
    private String memberLevel;

    public User() {
    }

    public User(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }
}
