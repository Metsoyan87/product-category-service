package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.Role;
import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {


    @MockBean  // moc anel imitacia anel vor ka baza
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    void save() {
        User user = User.builder()
                .id(1)
                .name("Gago")
                .surname("Sargsyan")
                .email("sargsyan@mail.ru")
                .password("198726")
                .role(Role.ADMIN)
                .product(null)

                .build();
        when(userRepository.save(any())).thenReturn(user);


        userService.save(User.builder()
                .name("Gago")
                .surname("Sargsyan")
                .email("sargsyan@mail.ru")
                .password("198726")
                .role(Role.ADMIN)
                .product(null)

                .build());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void saveNull() {
        User user = User.builder()
                .id(1)
                .name("Gago")
                .surname("Sargsyan")
                .email("sargsyan@mail.ru")
                .password("198726")
                .role(Role.ADMIN)
                .product(null)
                .build();

//        userService.save(user);
//        userService.deleteById(user.getId());
//        Optional<User> byId = userRepository.findById(user.getId());
//        assertFalse(byId.isPresent());

        when(userRepository.save(any())).thenReturn(user);

        assertThrows(RuntimeException.class, () -> {
            userService.save(null);
        });

        verify(userRepository, times(0)).save(any());
    }

    @Test
    void deleteByIdNotFound() {
        User user = User.builder()
                .id(1)
                .name("Gago")
                .surname("Sargsyan")
                .email("sargsyan@mail.ru")
                .password("198726")
                .role(Role.ADMIN)
                .product(null)
                .build();
        assertThrows(RuntimeException.class, () -> {
            userService.deleteById(1);
        });

    }


    @Test
    void getAllUser() {
        userService.getAllUser();
        verify(userRepository).findAll();
    }
}