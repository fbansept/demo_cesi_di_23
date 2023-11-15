package edu.cesi.demo.dao;

import edu.cesi.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User> findByLogin(String login);

}
