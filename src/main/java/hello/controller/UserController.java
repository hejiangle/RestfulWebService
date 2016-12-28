package hello.controller;

import hello.entity.User;
import hello.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by cqu on 23/12/2016.
 */
@RestController
@Resource(name = "DataService")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DataService dataService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<Integer,User>> getList(){
        System.out.println("Server will show all of users:");
        Map<Integer,User> userlist = dataService.getUserList();
        return ResponseEntity.ok(userlist);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable int id){
        User user = dataService.getUserById(id);
        if(user!=null) {
            return ResponseEntity.ok(user);
        }else{
            System.out.println("This user is not contained!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value="/{id}/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addUser(@PathVariable int id,@RequestParam String password,@RequestParam String name){
        User user = new User(password,name);
        URI location = null;
        try {
            location = new URI("http://localhost:8080/user/{id/add}");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        int result = dataService.addUser(id,user);
        System.out.println(result);
        return ResponseEntity.status(result).body(dataService.getUserList());
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteById(@PathVariable int id){
        int result = dataService.deleteUserById(id);
        System.out.println(result);
        return ResponseEntity.status(result).body(dataService.getUserList());
    }

/*
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestParam String password,@RequestParam String name){
        User user = new User(password,name);
        int result = dataService.deleteUser(user);
        System.out.println(result);
        return ResponseEntity.status(result).body(dataService.getUserList());
    }
*/

    @RequestMapping(value="/{id}/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestParam String password,@RequestParam String name){
        User user = new User(password,name);
        int result = dataService.updateUserById(id,user);
        System.out.println(result);
        return ResponseEntity.status(result).body(dataService.getUserList());
    }

}
