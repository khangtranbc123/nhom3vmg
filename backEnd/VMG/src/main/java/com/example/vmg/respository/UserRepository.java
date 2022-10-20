package com.example.vmg.respository;

import com.example.vmg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

//    Boolean existsByEmail(String email);
}
