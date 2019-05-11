package com.kev.Learner.repository;

import com.kev.Learner.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Query(value = "SELECT u FROM User u WHERE u.id =:id ")
    public User findByUserId(Long id);

}
