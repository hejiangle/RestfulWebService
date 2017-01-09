package hello.serviceImp;

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
    private UserRepository userRepository;//持久层接口

    private int statusCode; //返回状态码

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
                User temp = userRepository.findOne(user.getId());
                temp.setPassword(user.getPassword());
                userRepository.save(temp);
                statusCode = 200;
            } else{
                User temp = userRepository.findOne(user.getId());
                temp.setPassword(user.getPassword());
                temp.setName(user.getName());
                userRepository.save(temp);
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
    }
}