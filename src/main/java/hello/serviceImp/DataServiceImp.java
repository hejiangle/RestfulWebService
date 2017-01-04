package hello.serviceImp;


import hello.entity.Data;
import hello.entity.User;
import hello.repository.UserRepository;
import hello.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cqu on 27/12/2016.
 */

/**
 * 业务逻辑层
 * 对数据增删改查操作进行条件判断
 * 增、删、改的返回结果为状态码，查询操作则直接返回查询结果
 */
@Service
public class DataServiceImp implements DataService {

    @Autowired
    private Data data;//假数据

    @Autowired
    private UserRepository userRepository;//持久层接口

    private int statusCode; //返回状态码

    private boolean testFlag = true;//测试开关

    @Override
    public int addUser(User user) {
        Long id = user.getId();
        if(testFlag){
            if(data.get().containsKey(id)){
                System.out.println("This id is existing!");
                statusCode = 403;
            }else{
                if(isUserNameExisting(user)){
                    System.out.println("This name is existing!");
                    statusCode = 403;

                }else {
                    data.add(id,user);
                    statusCode = 201;

                }
            }
        }else{
            if(userRepository.exists(id)){
                System.out.println("This id is existing!");
                statusCode = 403;
            }else{
                if(isUserNameExisting(user)){
                    System.out.println("This name is existing!");
                    statusCode = 403;

                }else {
                    userRepository.save(user);
                    statusCode = 201;

                }
            }
        }
        return statusCode;
    }


    @Override
    public int deleteUserById(Long id) {
        if(testFlag) {
            if (!data.get().containsKey(id)) {
                System.out.println("This id is not existing!");
                statusCode = 404;
            } else {
                data.delete(id);
                statusCode = 204;
            }
        }else{
            if(userRepository.exists(id)){
                System.out.println("This id is not existing!");
                statusCode = 404;
            }else{
                userRepository.delete(id);
                statusCode = 204;
            }
        }
        return statusCode;
    }

    @Override
    public int updateUser(User user) {
        Long id = user.getId();
        if(testFlag) {
            if (data.get().containsKey(id)) {
                if (isUserExisting(user)) {
                    System.out.println("This user has been existing!");
                    statusCode = 403;
                } else if (isUserNameExisting(user)) {
                    System.out.println("This opration only change password!");
                    data.get(id).setPassword(user.getPassword());
                    statusCode = 200;
                } else {
                    data.get(id).setName(user.getName());
                    data.get(id).setPassword(user.getPassword());
                    statusCode = 200;
                }
            } else {
                System.out.println("This id is not existing!");
                System.out.println("System will create a new user!");
                statusCode = addUser(user);
            }
        }else{
            if(userRepository.exists(id)){
                if(isUserExisting(user)){
                    System.out.println("This user has been existing!");
                    statusCode = 403;
                }else if(isUserNameExisting(user)){
                    System.out.println("This opration only change password!");
                    userRepository.findOne(id).setPassword(user.getPassword());
                    statusCode = 200;
                } else{
                    userRepository.findOne(id).setName(user.getName());
                    userRepository.findOne(id).setPassword(user.getPassword());
                    statusCode = 200;
                }
            }else{
                System.out.println("This id is not existing!");
                System.out.println("System will create a new user!");
                statusCode = addUser(user);
            }
        }
        return statusCode;
    }

    @Override
    public List<User> getUserList() {
        if(testFlag) {
            List<User> userList = new ArrayList<>();
            for (User temp : data.get().values()) {
                userList.add(temp);
            }
            return userList;
        }else{
            List<User> userList = new ArrayList<User>();
            for(User temp : userRepository.findAll()){
                userList.add(temp);
            }
            return userList;
        }
    }

    @Override
    public User getUserById(Long id) {
        if(testFlag)
            return data.get(id);
        else
            return userRepository.findOne(id);
    }

    //下面是公共逻辑
    private boolean isUserNameExisting(User user){
        String name = user.getName();
        if(testFlag) {
            for (User temp : data.get().values()) {
                if (name.equals(temp.getName()))
                    return true;
            }
            return false;
        }else{
            for(User temp : userRepository.findAll()){
                if(name.equals(temp.getName()))
                    return true;
            }
            return false;
        }
    }

    private boolean isUserExisting(User user){
        String password = user.getPassword();
        String name = user.getName();
        if(testFlag){
            for(User temp : data.get().values()) {
                if (name.equals(temp.getName()) && password.equals(temp.getPassword()))
                    return true;
            }
            return false;
        }else{
            for(User temp : userRepository.findAll()) {
                if (name.equals(temp.getName()) && password.equals(temp.getPassword()))
                    return true;
            }
            return false;
        }
    }


/*
    //这是分割线！！！！下方是连接数据库的业务逻辑，上方是Map模拟数据库的业务逻辑
    @Override
    public int addUser(User user){
        if(userRepository.exists(user.getId())){
            System.out.println("This id is existing!");
            statusCode = 403;
        }else{
            if(isUserNameExisting(user)){
                System.out.println("This name is existing!");
                statusCode = 403;

            }else {
                userRepository.save(user);
                statusCode = 201;

            }
        }
        return statusCode;
    }

    @Override
    public int deleteUserById(Long id){
        if(userRepository.exists(id)){
            System.out.println("This id is not existing!");
            statusCode = 403;
        }else{
            userRepository.delete(id);
            statusCode = 200;
        }
        return statusCode;
    }

    @Override
    public int updateUser(User user){
        if(userRepository.exists(user.getId())){
            if(isUserExisting(user)){
                System.out.println("This user has been existing!");
                statusCode = 403;
            }else if(isUserNameExisting(user)){
                System.out.println("This opration only change password!");
                userRepository.findOne(user.getId()).setPassword(user.getPassword());
                statusCode = 200;
            } else{
                userRepository.findOne(user.getId()).setName(user.getName());
                userRepository.findOne(user.getId()).setPassword(user.getPassword());
                statusCode = 200;
            }
        }else{
            System.out.println("This id is not existing!");
            System.out.println("System will create a new user!");
            statusCode = addUser(user);
        }
        return statusCode;
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

    private boolean isUserExisting(User user){
        String password = user.getPassword();
        String name = user.getName();
        for(User temp : userRepository.findAll()) {
            if (name.equals(temp.getName()) && password.equals(temp.getPassword()))
                return true;
        }
        return false;
    }*/
}