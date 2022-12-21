package ch.zhaw.card2brain;

import ch.zhaw.card2brain.TestDataGenerator.TestDataGenerator;
import ch.zhaw.card2brain.dto.UserDto;
import ch.zhaw.card2brain.model.Card;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import ch.zhaw.card2brain.repository.DataAccess;
import ch.zhaw.card2brain.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Card2BrainDbEntityTests {

    private final DataAccess dataAccess;

    final String DEUTSCH = "Deutsch";
    final String GESCHICHTE = "Geschichte";
    final String INFORMATIK = "Informatik";

    @Autowired
    UserService userService;

    @Autowired
    Card2BrainDbEntityTests(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @BeforeEach
    public void init() {
        dataAccess.getCardRepository().deleteAll();
        dataAccess.getCategoryRepository().deleteAll();
        dataAccess.getUserRepository().deleteAll();
    }

    @Test
    void testUserService() {
        User user = TestDataGenerator.GET_DEFAULT_USER();

        UserDto userDto = userService.addUser(user);
        assertEquals("User added in Database.", userDto.getMessage());

        UserDto userDto2 = userService.addUser(user);
        assertEquals("User User1 exists already.", userDto2.getMessage());

    }

    @Test
    void testDefaultUser() {
        User user = TestDataGenerator.GET_DEFAULT_USER();
        dataAccess.getUserRepository().save(user);

        assertEquals(1, dataAccess.getUserRepository().findAll().size());

        dataAccess.getUserRepository().delete(user);
        assertEquals(0, dataAccess.getUserRepository().findAll().size());

    }

    @Test
    void testCustomUser() {
        final String testUser = "Testee";
        User user = TestDataGenerator.GET_TEST_USER(testUser);
        dataAccess.getUserRepository().save(user);

        assertEquals(testUser, dataAccess.getUserRepository().findAll().get(0).getUserName());

        dataAccess.getUserRepository().delete(user);
        assertEquals(0, dataAccess.getUserRepository().findAll().size());

    }

    @Test
    void testCategory() {

        //arrange
        final User TEST_USER = TestDataGenerator.GET_DEFAULT_USER();
        // genereate 3 categories and persist on DB
        Category category = TestDataGenerator.GET_TEST_CATEGORY(INFORMATIK, TEST_USER);
        Category category2 = TestDataGenerator.GET_TEST_CATEGORY(DEUTSCH, TEST_USER);
        Category category3 = TestDataGenerator.GET_TEST_CATEGORY(GESCHICHTE, TEST_USER);


        // persist user before persist category
        dataAccess.getUserRepository().save(category.getOwner());
        //persist 3 categories
        //test
        List<Category> categoriesToSaveOnDb = new ArrayList<>();
        categoriesToSaveOnDb.add(category);
        categoriesToSaveOnDb.add(category2);
        categoriesToSaveOnDb.add(category3);

        dataAccess.getCategoryRepository().saveAll(categoriesToSaveOnDb);

        //assert
        assertEquals(3, dataAccess.getCategoryRepository().findAll().size());


        List<Category> categoriesFromDb = dataAccess.getCategoryRepository().findCategoriesByOwner(category.getOwner());
        assertEquals(3, countRightCategoryNames(categoriesFromDb));

        //Delete first
        String deletedCategory = categoriesFromDb.get(0).getCategoryName();
        dataAccess.getCategoryRepository().deleteById(categoriesFromDb.get(0).getId());
        categoriesFromDb = dataAccess.getCategoryRepository().findCategoriesByOwner(category.getOwner());
        assertEquals(2, countRightCategoryNames(categoriesFromDb));
        assertTrue(isNotInList(categoriesFromDb, deletedCategory));
    }

    @Test
    void testCard() {
        //arrange
        final int ONE_HUNDRET = 100;
        final int TWO_HUNDRET = 200;
        Card card = TestDataGenerator.GET_DEFAULT_CARD();
        card.setCounterFalse(ONE_HUNDRET);
        card.setCounterRight(TWO_HUNDRET);
        final LocalDateTime date = LocalDateTime.now();
        card.setAnsweredLastTime(date);

        dataAccess.getUserRepository().save(card.getCategory().getOwner());
        dataAccess.getCategoryRepository().save(card.getCategory());

        //act
        dataAccess.getCardRepository().save(card);

        //assert
        List<Card> cards = dataAccess.getCardRepository().findCardByCategory(card.getCategory());
        assertEquals(1, cards.size());

        assertEquals(card.getCategory().getCategoryName(), cards.get(0).getCategory().getCategoryName());
        assertEquals(card.getCounterRight(), cards.get(0).getCounterRight());
        assertEquals(card.getCounterFalse(), cards.get(0).getCounterFalse());
        assertEquals(card.getAnsweredLastTime().truncatedTo(ChronoUnit.MINUTES), cards.get(0).getAnsweredLastTime().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(card.getAnswer(), cards.get(0).getAnswer());
        assertEquals(card.getQuestion(), cards.get(0).getQuestion());
    }

    private boolean isNotInList(List<Category> categories, String deletedCategory) {
        for (Category c : categories) {
            if (c.getCategoryName().equals(deletedCategory)) {
                return false;
            }
        }
        return true;
    }

    private int countRightCategoryNames(List<Category> categories) {

        int counter = 0;
        for (Category c : categories) {

            switch (c.getCategoryName()) {
                case DEUTSCH -> counter++;
                case GESCHICHTE -> counter++;
                case INFORMATIK -> counter++;
                default -> {
                }
            }
        }
        return counter;
    }
}
