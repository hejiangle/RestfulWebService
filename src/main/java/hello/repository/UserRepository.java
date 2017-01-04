package hello.repository;

import hello.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cqu on 03/01/2017.
 */
public interface UserRepository extends CrudRepository<User,Long> {

}
