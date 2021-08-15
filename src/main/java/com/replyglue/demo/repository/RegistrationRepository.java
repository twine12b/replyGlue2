package com.replyglue.demo.repository;

import com.replyglue.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user u WHERE u.username= ?1", nativeQuery = true)
    public User findUsersByUsername(String username);

    @Query(value = "SELECT * FROM user u WHERE u.card > 1", nativeQuery = true)
    public List<User> findUsersWithCreditCard();

    @Query(value = "SELECT * FROM user u WHERE u.card IS NULL", nativeQuery = true)
    public List<User> findUsersWithOutCreditCard();
}
