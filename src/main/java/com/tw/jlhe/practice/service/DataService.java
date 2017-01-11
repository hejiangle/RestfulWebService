package com.tw.jlhe.practice.service;

import com.tw.jlhe.practice.entity.User;
import java.util.List;


/**
 * Created by cqu on 27/12/2016.
 */

public interface DataService {

    public void createUser(User user) throws Exception;

    public void deleteUserById(Long id) throws Exception;

    //public void updateUser(User user) throws Exception;


    public User getUserById(Long id);

    public List<User> getUserList();
}
