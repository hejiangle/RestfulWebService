package hello.serviceImp;

import hello.entity.Data;
import hello.entity.User;
import hello.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqu on 04/01/2017.
 */
@Service
public class DataServiceImpMock implements DataService{

    @Autowired
    private Data data;//假数据

    private int statusCode; //返回状态码

    @Override
    public int addUser(User user) {
        Long id = user.getId();
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
        return statusCode;
    }

    @Override
    public int deleteUserById(Long id) {
        if (!data.get().containsKey(id)) {
            System.out.println("This id is not existing!");
            statusCode = 404;
        } else {
            data.delete(id);
            statusCode = 204;
        }
        return statusCode;
    }

    @Override
    public int updateUser(User user) {
        Long id = user.getId();
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
        return statusCode;
    }


    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        for (User temp : data.get().values()) {
            userList.add(temp);
        }
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        return data.get(id);
    }

    private boolean isUserNameExisting(User user){
        String name = user.getName();
        for (User temp : data.get().values()) {
            if (name.equals(temp.getName()))
                return true;
        }
        return false;
    }

    private boolean isUserExisting(User user){
        String password = user.getPassword();
        String name = user.getName();
        for(User temp : data.get().values()) {
            if (name.equals(temp.getName()) && password.equals(temp.getPassword()))
                return true;
        }
        return false;
    }




}