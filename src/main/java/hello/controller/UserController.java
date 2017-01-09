package hello.controller;

import hello.entity.User;
import hello.model.*;
import hello.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
@Resource(name = "DataService")
public class UserController {

    @Autowired
    @Qualifier("dataServiceImp")
    private DataService dataService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<IndexModel> index() throws MalformedURLException {
        return ResponseEntity.ok(new IndexModel());
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserListModel> getList() throws MalformedURLException {
        System.out.println("Server will show all of users:");
        List<User> users = dataService.getUserList();
        URL url = new URL("http://localhost:8080/users");
        String title = "Get the user Id is ";
        String type = "application/json";
        List<UserModel> tempList = new ArrayList<>();
        for(User temp : users){
            LinkModel linkModel = new LinkModel();
            linkModel.setRel("Collection "+url.toString()+"/"+temp.getId());
            linkModel.setHref(new URL(url+"/"+temp.getId()));
            linkModel.setTitle(title+temp.getId());
            linkModel.setType(type);
            UserModel userModel = new UserModel(temp,linkModel);
            tempList.add(userModel);
        }
        UserListModel userListModel = new UserListModel(tempList);
        return ResponseEntity.ok(userListModel);
    }

    @RequestMapping(value="/users/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        User user = dataService.getUserById(id);
        if(user!=null) {
            return ResponseEntity.ok(user);
        }else{
            ResultModel resultModel = new ResultModel();
            resultModel.setResult("This user is not contained!");
            System.out.println("This user is not contained!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultModel);
        }
    }

    @RequestMapping(value="/users",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody User user){
        //User user = new User(id,password,name);
        int result = dataService.addUser(user);
        System.out.println(result);
        ResultModel resultModel = new ResultModel();
        if(result==403)
            resultModel.setResult("This User's id or name is existing!");
        else
            resultModel.setResult("The User is created successfully!");
        return ResponseEntity.status(result).body(resultModel);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        int result = dataService.deleteUserById(id);
        System.out.println(result);
        ResultModel resultModel = new ResultModel();
        if(result==403)
            resultModel.setResult("This User is not existing!");
        else
            resultModel.setResult("The User is deleted successfully!");
        return ResponseEntity.status(result).body(resultModel);
    }


    @RequestMapping(value="/users/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
        //User user = new User(id,password,name);
        int result = dataService.updateUser(user);
        System.out.println(result);
        ResultModel resultModel = new ResultModel();
        if(result==403)
            resultModel.setResult("This User's id or name is existing!");
        else
            resultModel.setResult("The User is updated successfully!");
        return ResponseEntity.status(result).body(resultModel);
    }

}
