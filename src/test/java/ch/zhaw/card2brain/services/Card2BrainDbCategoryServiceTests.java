package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.TestDataGenerator.TestDataGenerator;
import ch.zhaw.card2brain.dto.CategoryDto;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import ch.zhaw.card2brain.repository.DataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Card2BrainDbCategoryServiceTests {

    private static final String GESCHICHTE = "Geschichte";
    private static final String MUSIK = "Musik";
    private static final String DEUTSCH = "Deutsch";
    private static final String GEOMETRIE = "Geometrie";
    private final DataAccess dataAccess;
    @Autowired
    CategoryService categoryService;
    @Autowired
    Card2BrainDbCategoryServiceTests(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @BeforeEach
    public void init() {
        dataAccess.getCardRepository().deleteAll();
        dataAccess.getCategoryRepository().deleteAll();
        dataAccess.getUserRepository().deleteAll();
    }

    @Test
    void testCategoryServiceUserDoesNotExists() {
        //arrange
        User user = TestDataGenerator.GET_DEFAULT_USER();
        Category category = TestDataGenerator.GET_DEFAULT_CATEGORY();
        //act
        CategoryDto categoryDto;
        categoryDto = categoryService.addCategoryToUser(user, category);
        //assert
        assertEquals("Something went wrong : User :User1 does not exist.", categoryDto.getMessage());
        assertEquals(category.getOwner().getUserName(), categoryDto.getUserName());

    }

    @Test
    void testCategoryServiceCategoryAdded() {
        //arrange
        User user = TestDataGenerator.GET_DEFAULT_USER();
        dataAccess.getUserRepository().save(user);
        Category category = TestDataGenerator.GET_DEFAULT_CATEGORY();
        //act
        CategoryDto categoryDto;
        categoryDto = categoryService.addCategoryToUser(user, category);
        //assert
        assertEquals("Category " + TestDataGenerator.DEFAULT_CATEGORY1 + " has been added to user.", categoryDto.getMessage());

    }

    @Test
    void testCategoryServiceGetAllCategoriesToUser() {
        //arrange
        User user = TestDataGenerator.GET_DEFAULT_USER();
        List<Category> compareCategories = new ArrayList<>();
        compareCategories.add(TestDataGenerator.GET_TEST_CATEGORY(GEOMETRIE, user));
        compareCategories.add(TestDataGenerator.GET_TEST_CATEGORY(GESCHICHTE, user));
        compareCategories.add(TestDataGenerator.GET_TEST_CATEGORY(DEUTSCH, user));
        compareCategories.add(TestDataGenerator.GET_TEST_CATEGORY(MUSIK, user));

        dataAccess.getUserRepository().save(user);
        dataAccess.getCategoryRepository().saveAll(compareCategories);

        //act
        CategoryDto categoryDto = categoryService.getAllCategoriesToUser(user);

        //assert
        assertEquals(categoryDto.getUserName(), user.getUserName());
        assertEquals(categoryDto.getFirstName(), user.getFirstName());
        assertEquals(categoryDto.getMailAddress(), user.getMailAddress());
        assertEquals(categoryDto.getCategories().size(), categoryDto.getCategories().size());
        assertTrue(correctCategories(categoryDto.getCategories(), compareCategories));
    }

    private boolean correctCategories(List<Category> actualCategories, List<Category> compareCategories) {
        int right = 0;
        for (Category actual : actualCategories) {
            for (Category compare : compareCategories) {
                if (actual.getCategoryName().contains(compare.getCategoryName()) &&
                        actual.getOwner().getUserName().contains(compare.getOwner().getUserName()) &&
                        actual.getOwner().getFirstName().contains(compare.getOwner().getFirstName()) &&
                        actual.getOwner().getMailAddress().contains(compare.getOwner().getMailAddress())) {
                    right++;

                }
            }
        }


        return right == actualCategories.size() ? true : false;
    }

}
