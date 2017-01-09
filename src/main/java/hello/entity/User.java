package hello.entity;

/**
 * Created by cqu on 23/12/2016.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 实体类
 *
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //主键自动增加
    private Long id;
    private String password;
    private String name;

    public User(){

    }

    public User(String password,String name){
        this.password = password;
        this.name = name;
    }

    public User(Long id,String password,String name){
        this.id =id;
        this.password = password;
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
