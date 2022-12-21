package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.TestDataGenerator.TestDataGenerator;
import ch.zhaw.card2brain.dto.UserDto;
import ch.zhaw.card2brain.model.User;
import ch.zhaw.card2brain.repository.DataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Card2BrainDbUserServiceTests {

    private final DataAccess dataAccess;

    @Autowired
    UserService userService;

    @Autowired
    Card2BrainDbUserServiceTests(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @BeforeEach
    public void init() {
        dataAccess.getCardRepository().deleteAll();
        dataAccess.getCategoryRepository().deleteAll();
        dataAccess.getUserRepository().deleteAll();
    }

    @Test
    void testUserServiceUserAdded() {
        //arrange
        User user = TestDataGenerator.GET_DEFAULT_USER();
        //act
        UserDto userDto = userService.addUser(user);
        //assert
        assertEquals("User added in Database.", userDto.getMessage());

    }

    @Test
    void testUserServiceUserExistsAlready() {
        //arrange
        User user = TestDataGenerator.GET_DEFAULT_USER();
        userService.addUser(user);
        //act
        UserDto userDto = userService.addUser(user);

        //assert
        assertEquals("User User1 exists already.", userDto.getMessage());
    }

}
