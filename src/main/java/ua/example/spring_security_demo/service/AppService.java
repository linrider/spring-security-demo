package ua.example.spring_security_demo.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import ua.example.spring_security_demo.domain.AppUser;
import ua.example.spring_security_demo.domain.Application;
import ua.example.spring_security_demo.repository.UserRepository;

@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applications;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;     


    @PostConstruct
    public void LoadAppToDb() {
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1, 100)
            .mapToObj(i -> Application.builder()
                .id(i)
                .name(faker.app().name())
                .author(faker.app().author())
                .version(faker.app().version())
                .build()).toList();
    }

    public List<Application> getApplications() {
        return applications;
    }

    public Application getApplicationById(int id) {
        return applications.stream()
            .filter(app -> app.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void addUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
