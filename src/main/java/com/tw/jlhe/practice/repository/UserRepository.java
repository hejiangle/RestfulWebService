package com.tw.jlhe.practice.repository;

import com.tw.jlhe.practice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
