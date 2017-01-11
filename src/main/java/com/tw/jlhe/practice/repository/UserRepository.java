package com.tw.jlhe.practice.repository;

import com.tw.jlhe.practice.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cqu on 03/01/2017.
 */
public interface UserRepository extends CrudRepository<User,Long> {

}
