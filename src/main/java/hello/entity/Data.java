package hello.entity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cqu on 27/12/2016.
 */
@Component
public class Data {

   private Map<Integer,User> dataSource = new HashMap<>();
   private String status;

   public Data(){
       status = "";
       User user_1 = new User("asd","Leo");
       User user_2 = new User("qwe","Oli");
       dataSource.put(9527,user_1);
       dataSource.put(2048,user_2);
   }

   public String add(int id,User user){
       if(dataSource.containsKey(id)){
           System.out.println("This id is existing!");
           status = "403";
       }else{
           if(isExisting(user)){
               System.out.println("This name is existing!");
               status = "403";
           }else {
               dataSource.put(id, user);
               status = "201";
           }
       }
       return status;
   }

   public String delete(int id){
       if(!dataSource.containsKey(id)){
           System.out.println("This id is not existing!");
           status = "404";
       }else{
           dataSource.remove(id);
           status = "204";
       }
       return status;
   }

   public String delete(User user){
       if(!isExisting(user)){
           System.out.println("This user is not existing!");
           status = "404";
       }else{
           dataSource.values().remove(user);
           System.out.println("The delete operation is successful!");
           status = "204";
       }
       return status;
   }

   public String update(int id ,User user){
       if(dataSource.containsKey(id)){
           if(isExisting(user)){
               System.out.println("This user has been existing!");
               status = "403";
           }else{
               dataSource.get(id).setName(user.getName());
               dataSource.get(id).setPassword(user.getPassword());
               status = "200";
           }
       }else{
           System.out.println("This id is not existing!");
           System.out.println("System will create a new user!");
           status = add(id,user);
       }
       return status;
   }

   public Map<Integer,User> get(){
       return dataSource;
   }

   public User get(int id){
       return dataSource.get(id);
   }

    private boolean isExisting(User user){
        String name = user.getName();
        for(User temp : dataSource.values()){
            if(name.equals(temp.getName()))
                return true;
        }
        return false;
    }

}