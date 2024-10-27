package ua.example.spring_security_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.example.spring_security_demo.domain.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{

    Optional<AppUser> findByName(String name);
    

}
