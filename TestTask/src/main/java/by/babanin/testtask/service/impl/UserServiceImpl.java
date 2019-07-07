package by.babanin.testtask.service.impl;

import by.babanin.testtask.dao.UserRepository;
import by.babanin.testtask.entity.User;
import by.babanin.testtask.entity.UserRole;
import by.babanin.testtask.service.UserService;
import by.babanin.testtask.service.exception.UserExistsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    @Override
    public User addUser(User user) {
        User userByUsername = getUserByUsername(user.getUsername());
        if (!Objects.isNull(userByUsername))
            throw new UserExistsException("Порльзователь с username " + user.getUsername() + " существует");
        user.setRoles(Collections.singleton(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
