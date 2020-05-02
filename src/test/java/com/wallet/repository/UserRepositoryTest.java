package com.wallet.repository;

import com.wallet.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    private static final String EMAIL = "teste@teste.com";


    @Autowired
    private UserRepository userRepository;

    @Before
    public void init(){
        User user = new User();
        user.setName("test init");
        user.setPassword("senha34561");
        user.setEmail(EMAIL);
        userRepository.save(user);

    }
    @After
    public void finish(){
        userRepository.deleteAll();

    }

    @Test
    public void testSave(){
        User user = new User();
        user.setName("teste");
        user.setPassword("12345");
        user.setEmail("teste@teste.com");

        User response = userRepository.save(user);
        assertNotNull(response);
    }
    @Test
    public void testFindByEmail(){
        Optional<User> response = userRepository.findByEmailEquals(EMAIL);
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}
