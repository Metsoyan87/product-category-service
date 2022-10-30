package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.dto.*;
import com.example.productcategoryservice.maper.UserMapper;
import com.example.productcategoryservice.model.Role;
import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.repository.UserRepository;
import com.example.productcategoryservice.service.UserService;
import com.example.productcategoryservice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserRepository userRepository;

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        Optional<User> existingUser = userRepository.findByEmail(createUserDto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = userMapper.map(createUserDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userMapper.map(userRepository.save(user)));
    }

    @PostMapping("/user/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userRepository.findByEmail(userAuthDto.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .user(userMapper.map(user))
                        .build()
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody SaveUserDto saveUserDto,
                                              @PathVariable("id") int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User byId = user.get();
        if (saveUserDto.getEmail() != null) {
            byId.setEmail(saveUserDto.getEmail());
        }
        if (saveUserDto.getName() != null) {
            byId.setName(saveUserDto.getName());
        }
        if (saveUserDto.getSurname() != null) {
            byId.setSurname(saveUserDto.getSurname());
        }
        if (saveUserDto.getPassword() != null) {
            byId.setPassword(saveUserDto.getPassword());
        }
        userRepository.save(byId);
        UserDto userResponseDto = userMapper.map(byId);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUser();
    }
}
