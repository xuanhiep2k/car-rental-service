package com.example.carrental.services;

import com.example.carrental.model.Role;
import com.example.carrental.model.User;
import com.example.carrental.repositories.RoleRepository;
import com.example.carrental.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User founded", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(user -> {
            user.setPassword("");
            return user;
        }).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        User newUser = new User(null, user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), user.getFullName(), user.getAddress(), user.getTel(), user.getNote(), user.getSeniority(), new ArrayList<>());
        newUser.getRoles().addAll(
                user.getRoles().stream().map(r -> {
                    Optional<Role> role = roleRepository.findById(r.getId());
                    role.get().getUsers().add(newUser);
                    return role.get();
                }).collect(Collectors.toList())
        );
        return userRepository.save(newUser);
    }

}
