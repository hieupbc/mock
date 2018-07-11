package com.example.mockbus.services.Impl;

import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.IllegalFormatException;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void isExistTest() {
        assertTrue(userService.isExist("hieupbc@gmail.com"));
        assertFalse(userService.isExist("adsads"));
        assertFalse(userService.isExist(null));
    }


    @Test
    public void findAllTest() {
        assertEquals(5,userService.findAll().size());
    }

    @Test
    public  void testCreate(){
        UserDomain userDomain = userService.create(new UserDomain("test", "abc@gmail.com", "123"));
        UserDomain userDomain1 = userService.findByEmail(userDomain.getEmail()).get();
        assertEquals(userDomain1.getPassword(),"123");
        assertEquals(userDomain.getRoles(),userDomain1.getRoles());
        assertEquals(userDomain.getPassword(),userDomain1.getPassword());
        assertEquals(userDomain.getName(),userDomain1.getName());
        assertEquals(userDomain.getId(),userDomain1.getId());
    }


    @Test
    public void findByNameLike() {
        List<UserDomain> hieu = userService.findByNameIsLike("Hieu");
        assertEquals(1,hieu.size());
    }
}