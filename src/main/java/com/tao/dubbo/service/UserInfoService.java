package com.tao.dubbo.service;

import com.tao.dubbo.entity.UserInfo;

import java.util.List;

/**
 * Created by DELL on 2017/9/22.
 */
public interface UserInfoService {

    void insert(UserInfo userInfo);

    void delete(String key);

    List<UserInfo> getList();

    UserInfo getUserInfo(String key);
}
