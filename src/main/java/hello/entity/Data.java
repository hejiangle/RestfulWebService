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

   public void add(Long id,User user){
       user.setId(id);
       dataSource.put(id,user);
   }

   public void delete(Long id){
       dataSource.remove(id);
   }


   public Map<Long,User> get(){
       return dataSource;
   }

   public User get(Long id){
       return dataSource.get(id);
   }

}
