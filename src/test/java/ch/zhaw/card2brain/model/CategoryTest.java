package ch.zhaw.card2brain.model;

import ch.zhaw.card2brain.TestDataGenerator.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    private User defaultUser;

    @BeforeEach
    public void getUser() {

        this.defaultUser = TestDataGenerator.GET_DEFAULT_USER();

    }

    @Test
    void getCategoryName() {
        Category category = TestDataGenerator.GET_DEFAULT_CATEGORY();
        assertEquals(TestDataGenerator.DEFAULT_CATEGORY1, category.getCategoryName());

    }
}
