package com.projects.testContainers.db.repository;

import com.projects.testContainers.db.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);

}
