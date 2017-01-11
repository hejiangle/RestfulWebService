package com.tw.jlhe.practice.model;

import com.tw.jlhe.practice.entity.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by cqu on 11/01/2017.
 */
public class UserResult extends ResourceSupport {

    private User userInfo;


    public UserResult(){

    }

    public UserResult(User user){
        userInfo = user;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}
