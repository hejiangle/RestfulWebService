package com.tw.jlhe.practice.controller;

import com.tw.jlhe.practice.entity.User;
import com.tw.jlhe.practice.model.*;
import com.tw.jlhe.practice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller 用来根据URI调用不同的业务方法
 * 返回处理结果：
 * 列表直接返回一个list
 * 对象查询则返回查询对象user
 *
 */
@RestController
public class UserController {

    @Autowired
    @Qualifier("userServiceImp")
    private UserService userService;

    @RequestMapping(value = "/users" , method = RequestMethod.GET)
    public ResponseEntity<?> getList() throws MalformedURLException {
        List<User> users = userService.getUserList();
        List<UserResult> tempList = new ArrayList<>();
        for(User temp : users){
            UserResult userResult = new UserResult(temp);
            userResult.add(new Link("http://localhost:8080/users/"+temp.getId()));
            tempList.add(userResult);
        }
        return ResponseEntity.ok(tempList);
    }

    @RequestMapping(value="/users/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResult(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/users" , method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.createUser(user);
            return ResponseEntity.ok(new MessageResult("User " + user.getName() + " is created successfully!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResult(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/users/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok(new MessageResult("Delete user is successful!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResult(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/users/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
        try {
            user.setId(id);
            userService.updateUser(user);
            return ResponseEntity.ok(new MessageResult("Update user is successful!"));
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResult(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

}
