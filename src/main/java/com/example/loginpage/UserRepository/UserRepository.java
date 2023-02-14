package com.example.loginpage.UserRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);
}
