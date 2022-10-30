package com.example.productcategoryservice.service;

import com.example.productcategoryservice.maper.UserMapper;
import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getAllUser(){
       return userRepository.findAll();
    }
    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

}
