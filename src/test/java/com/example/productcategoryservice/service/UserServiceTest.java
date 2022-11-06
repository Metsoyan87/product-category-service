package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.Role;
import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @MockBean  // moc anel imitacia anel
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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
        when(userRepository.save(any())).thenReturn(user);

        assertThrows(RuntimeException.class, () -> {
            userService.save(null);
        });

        verify(userRepository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        User user = User.builder()
                .id(1)
                .name("Gago")
                .surname("Sargsyan")
                .email("sargsyan@mail.ru")
                .password("198726")
                .role(Role.ADMIN)
                .product(null)
                .build();

        userService.save(user);
        userService.deleteById(user.getId());
        Optional<User> byId = userRepository.findById(user.getId());
        assertFalse(byId.isPresent());
    }


        @Test
    void deleteByIdNull() {
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
            userService.deleteById(999);
        });

    }
//    @Test
//    void deleteById_notFound() {
//        EmptyResultDataAccessException thrown = assertThrows(EmptyResultDataAccessException.class, () -> {
//            userService.deleteById(9999);
//        });
//        assertEquals("No class com.example.myitemsrest.entity.User entity with id 9999 exists!", thrown.getMessage());
//    }

    @Test
    void getAllUser() {
        userService.getAllUser();
        verify(userRepository).findAll();
    }
}