package com.replyglue.demo.repository;

import com.replyglue.demo.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM user u WHERE u.username= ?1", nativeQuery = true)
    public User findUsersByUsername(String username);
}
