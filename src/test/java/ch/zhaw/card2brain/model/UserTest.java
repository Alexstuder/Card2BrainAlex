package ch.zhaw.card2brain.model;

import ch.zhaw.card2brain.TestDataGenerator.TestDataGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void getName() {
        User testUser = TestDataGenerator.GET_DEFAULT_USER();
        assertEquals(TestDataGenerator.DEFAULT_USER_NAME, testUser.getUserName());
    }

    @Test
    void setName() {

        final String USER_NAME = "User2";
        User testUser = new User();
        testUser.setUserName("User2");
        assertEquals(testUser.getUserName(), USER_NAME);
    }
}
