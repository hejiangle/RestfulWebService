package com.tw.jlhe.practice.service;

import com.tw.jlhe.practice.entity.User;
import java.util.List;


public interface UserService {

    public void createUser(User user) throws Exception;

    public void deleteUserById(Long id) throws Exception;

    public void updateUser(User user) throws Exception;


    public User getUserById(Long id);

    public List<User> getUserList();
}
