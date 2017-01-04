package hello.entity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cqu on 27/12/2016.
 */

/**
 * 通过Map模拟数据库和数据存取层
 * 在创建对象时初始化数据
 * 提供简单数据新增、删除、查询方法
 */

@Component
public class Data {

   private Map<Long,User> dataSource = new HashMap<>();

   public Data(){
       User user_1 = new User(9527L,"asd","Leo");
       dataSource.put(9527L,user_1);
   }

   public void setDataSource(Map<Long,User> dataSource){
       this.dataSource = dataSource;
   }


   /*public boolean add(int id,User user) {
       if(dataSource.containsKey(id)){
           System.out.println("This id is existing!");
           flag = false;
       }else{
           if(isUserNameExisting(user)){
               System.out.println("This name is existing!");
               flag = false;

           }else {
               dataSource.put(id, user);
               flag = true;

           }
       }
       return flag;
   }*/

   public void add(Long id,User user){
       user.setId(id);
       dataSource.put(id,user);
   }

   /*public boolean delete(int id){
       if(!dataSource.containsKey(id)){
           System.out.println("This id is not existing!");
           flag = false;
       }else{
           dataSource.remove(id);
           flag = true;
       }
       return flag;
   }*/
   public void delete(Long id){
       dataSource.remove(id);
   }

  /* public boolean update(int id ,User user){
       if(dataSource.containsKey(id)){
           if(isUserExisting(user)){
               System.out.println("This user has been existing!");
               flag = false;
           }else if(isUserNameExisting(user)){
               System.out.println("This opration only change password!");
               dataSource.get(id).setPassword(user.getPassword());
               flag = true;
           } else{
               dataSource.get(id).setName(user.getName());
               dataSource.get(id).setPassword(user.getPassword());
               flag = true;
           }
       }else{
           System.out.println("This id is not existing!");
           System.out.println("System will create a new user!");
           flag = add(id,user);
       }
       return flag;
   }*/

   public Map<Long,User> get(){
       return dataSource;
   }

   public User get(Long id){
       return dataSource.get(id);
   }

    private boolean isUserNameExisting(User user){
        String name = user.getName();
        for(User temp : dataSource.values()){
            if(name.equals(temp.getName()))
                return true;
        }
        return false;
    }

    private boolean isUserExisting(User user){
        String password = user.getPassword();
        String name = user.getName();
        for(User temp : dataSource.values()) {
            if (name.equals(temp.getName()) && password.equals(temp.getPassword()))
                return true;
        }
        return false;
    }

}
