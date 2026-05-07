package aiss.DailyMotionMiner.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.DailyMotionMiner.model.modelDM.user.UserList;
 
@SpringBootTest
public class UserDMServiceTest {
    @Autowired
    UserDMService userDMService;

    @Test
    @DisplayName("Test getUsers")
    public void testGetUsers() {
        List<UserList> users = userDMService.getUsers();
        assertNotNull(users, "The list of users should not be null");
        assertFalse(users.isEmpty(), "The list of users should not be empty");
        assertNotNull(users.get(0).getId(), "The first user should have an ID");
        assertNotNull(users.get(0).getScreenname(), "The first user should have a screen name");
        assertNotNull(users.get(0).getUrl(), "The first user should have a URL");
        assertNotNull(users.get(0).getAvatar120Url(), "The first user should have an avatar URL");
    }

    @Test
    @DisplayName("Test getUserById")
    public void testGetUserById() {
        String userId = "x4y70z4"; // Reemplaza con un ID de usuario válido
        UserList user = userDMService.getUserById(userId);
        assertNotNull(user, "The user should not be null");
        assertNotNull(user.getId(), "The user should have an ID");
        assertNotNull(user.getScreenname(), "The user should have a screen name");
        assertNotNull(user.getUrl(), "The user should have a URL");
        assertNotNull(user.getAvatar120Url(), "The user should have an avatar URL");
    }
}
