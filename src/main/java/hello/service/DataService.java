package hello.service;

import hello.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by cqu on 27/12/2016.
 */

public interface DataService {

    public int addUser(int id,User user);

    //public int deleteUser(User user);

    public int deleteUserById(int id);

    public int updateUserById(int id,User user);

    public Map<Integer,User> getUserList();

    public User getUserById(int id);


}
