package hello.serviceImp;


import hello.entity.Data;
import hello.entity.User;
import hello.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by cqu on 27/12/2016.
 */
@Service
public class DataServiceImp implements DataService {

    @Autowired
    private Data data;

    @Override
    public int addUser(int id,User user) {
         if(data.add(id,user).equals("201")){
             return 201;
         }else{
             return 403;
         }
    }

    @Override
    public int deleteUser(User user) {
        if(data.delete(user).equals("204")){
            return 204;
        }else{
            return 404;
        }
    }

    @Override
    public int deleteUserById(int id) {
        if(data.delete(id).equals("204")){
            return 204;
        }else {
            return 404;
        }
    }

    @Override
    public int updateUserById(int id, User user) {
        String status = data.update(id,user);
        if(status.equals("200")){
            return 200;
        }else if(status.equals("201")){
            return 201;
        }else{
            return 403;
        }
    }

    @Override
    public Map<Integer,User> getUserList() {
        return data.get();
    }

    @Override
    public User getUserById(int id) {
        return data.get(id);
    }
}
