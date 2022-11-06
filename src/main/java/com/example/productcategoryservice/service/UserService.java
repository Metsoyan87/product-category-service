package com.example.productcategoryservice.service;

import com.example.productcategoryservice.dto.UserSaveDto;
import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void save(User user) {
        if (user == null) {
            throw new RuntimeException("User can't be null");
        }
        userRepository.save(user);
    }

    public void deleteById(int id) {
        if (userRepository.findById(id).isEmpty()){
            throw new RuntimeException("User can not a found");
        }
        userRepository.deleteById(id);

    }

    public void register(User user) {
        userRepository.save(user);
    }


}
