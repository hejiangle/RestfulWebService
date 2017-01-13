package com.tw.jlhe.practice.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体类
 *
 */
@Entity
public class User implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) //主键自动增加
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    public User(){

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

}
