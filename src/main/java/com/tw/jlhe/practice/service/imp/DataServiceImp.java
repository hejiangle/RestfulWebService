package com.tw.jlhe.practice.service.imp;

import com.tw.jlhe.practice.repository.UserRepository;
import com.tw.jlhe.practice.entity.User;
import com.tw.jlhe.practice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cqu on 27/12/2016.
 */

/**
 * 业务逻辑层
 * 对数据增删查操作进行条件判断
 * 增、删结果为状态码，查询操作则直接返回查询结果
 *
 */
@Service
public class DataServiceImp implements DataService {

    @Autowired
    private UserRepository userRepository;//持久层接口


    @Override
    public void createUser(User user) throws Exception {
        if(userRepository.exists(user.getId())||isUserNameExisting(user)){
            throw new Exception("This User has been existing!");
        } else {
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(Long id) throws Exception{
        if(!userRepository.exists(id)){
            throw new Exception("This id is not existing!");
        }else{
            userRepository.delete(id);
        }
    }

    @Override
    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>();
        for(User temp : userRepository.findAll()){
            userList.add(temp);
        }
        return userList;
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findOne(id);
    }

    //下面是公共逻辑
    private boolean isUserNameExisting(User user){
        String name = user.getName();
        for(User temp : userRepository.findAll()){
            if(name.equals(temp.getName()))
                return true;
        }
        return false;
    }
}