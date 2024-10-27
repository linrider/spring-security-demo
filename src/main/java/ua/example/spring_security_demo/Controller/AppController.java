package ua.example.spring_security_demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ua.example.spring_security_demo.domain.AppUser;
import ua.example.spring_security_demo.domain.Application;
import ua.example.spring_security_demo.service.AppService;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the unprotected area!";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> getAllApps() {
        return appService.getApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application getAppById(@PathVariable int id) {
        return appService.getApplicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody AppUser user) {
        appService.addUser(user);
        return "User added successfully!";
    }
    
}
