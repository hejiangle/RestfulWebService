package hello.controller;

import hello.entity.User;
import hello.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cqu on 23/12/2016.
 */

/**
 * Controller 用来根据URI调用不同的业务方法
 * 返回处理结果：
 * 列表直接返回一个list
 * 对象查询则返回查询对象user
 * 新建用户返回成功返回201状态，并显示list;失败则返回403状态
 *
 */
@RestController
@RequestMapping(value = "/users")
@Resource(name = "DataService")
public class UserController {

    @Autowired
    @Qualifier("dataServiceImp")
    private DataService dataService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getList(){
        System.out.println("Server will show all of users:");
        List<User> users = dataService.getUserList();
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        User user = dataService.getUserById(id);
        if(user!=null) {
            return ResponseEntity.ok(user);
        }else{
            System.out.println("This user is not contained!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value="/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addUser(@PathVariable Long id,@RequestBody User user){
        //User user = new User(id,password,name);
        int result = dataService.addUser(user);
        System.out.println(result);
        return ResponseEntity.status(result).body(dataService.getUserList());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        int result = dataService.deleteUserById(id);
        System.out.println(result);
        return ResponseEntity.status(result).build();
    }


    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
        //User user = new User(id,password,name);
        int result = dataService.updateUser(user);
        System.out.println(result);
        return ResponseEntity.status(result).body(dataService.getUserList());
    }

}
