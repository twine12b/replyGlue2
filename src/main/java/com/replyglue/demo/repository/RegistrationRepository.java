package com.replyglue.demo.repository;

import com.replyglue.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user u WHERE u.username= ?1", nativeQuery = true)
    User findUsersByUsername(String username);

    @Query(value = "SELECT * FROM user u WHERE u.card > 1", nativeQuery = true)
    List<User> findUsersWithCreditCard();

    @Query(value = "SELECT * FROM user u WHERE u.card IS NULL", nativeQuery = true)
    List<User> findUsersWithOutCreditCard();

    @Query(value = "SELECT * FROM user u WHERE u.card= ?1", nativeQuery = true)
    Optional<User> findUserByCreditCard(String cardNumber);
}
