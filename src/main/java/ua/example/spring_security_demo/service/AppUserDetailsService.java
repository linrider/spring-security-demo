package ua.example.spring_security_demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.example.spring_security_demo.config.AppUserDetails;
import ua.example.spring_security_demo.domain.AppUser;
import ua.example.spring_security_demo.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = repository.findByName(username);
        return user.map(AppUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

}
