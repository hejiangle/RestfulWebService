package hello.service;

import hello.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cqu on 27/12/2016.
 */

public interface DataService {

    public int addUser(User user);

    public int deleteUserById(Long id);

    public int updateUser(User user);

//    public Map<Integer,User> getUserList();

    public User getUserById(Long id);

    public List<User> getUserList();
}
