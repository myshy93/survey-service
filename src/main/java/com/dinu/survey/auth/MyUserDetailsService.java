package com.dinu.survey.auth;

import com.dinu.survey.entity.User;
import com.dinu.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        // check if user has a value or throw exception
        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + username));

        //map Optional object to MyUserDetails object
        return user.map(MyUserDetails::new).get();
    }
}
