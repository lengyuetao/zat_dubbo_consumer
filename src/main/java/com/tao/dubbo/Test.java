package com.tao.dubbo;

import com.tao.dubbo.entity.UserInfo;
import com.tao.dubbo.service.UserInfoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by DELL on 2017/9/20.
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:config/applicationContext-dubboConsumer.xml"});
//        context.start();
//
//        UserInfoService userInfoService= (UserInfoService) context.getBean("userInfoService");
//
//        UserInfo userInfo=new UserInfo();
//
//        userInfo.setId(1L);
//        userInfo.setUserName("aaa");
//        userInfo.setPwd("23423423");
//        userInfo.setAddress("kkkkksdf");
//        userInfo.setPhone("2342342342");
//        userInfo.setRegTime(new Date());
//
//        userInfoService.insert(userInfo);

        //System.in.read(); // 按任意键退出
    }
}
