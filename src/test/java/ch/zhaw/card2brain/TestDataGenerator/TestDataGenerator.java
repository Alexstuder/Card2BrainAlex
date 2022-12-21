package ch.zhaw.card2brain.TestDataGenerator;

import ch.zhaw.card2brain.model.Card;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    public static final String DEFAULT_USER_NAME = "User1";
    public static final String DEFAULT_CATEGORY1 = "Mathematik";

    public static final String QUESTION = "Frage";
    public static final String ANSWER = "Antwort";


    public static User GET_DEFAULT_USER() {
        return GET_TEST_USER(DEFAULT_USER_NAME);
    }

    public static User GET_TEST_USER(String name) {
        User testUser = new User();
        testUser.setUserName(name);
        testUser.setFirstName("FirstName1");
        testUser.setMailAddress("MailAddress1");
        return testUser;
    }

    public static Category GET_DEFAULT_CATEGORY() {
        return GET_TEST_CATEGORY(DEFAULT_CATEGORY1, TestDataGenerator.GET_DEFAULT_USER());
    }

    public static Category GET_DEFAULT_CATEGORY_TO_USER(User user) {
        return GET_TEST_CATEGORY(DEFAULT_CATEGORY1, user);
    }

    public static Category GET_TEST_CATEGORY(String categoryName, User user) {
        return new Category(categoryName, user);
    }

    public static Card GET_DEFAULT_CARD() {
        return GET_TEST_CARD(TestDataGenerator.GET_DEFAULT_CATEGORY());
    }

    public static Card GET_TEST_CARD(Category category) {
        return new Card("Frage", "Antwort", category);
    }

    public static List<Card> GET_TEST_CARDS(Category category) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Card card = new Card(QUESTION + " " + i, ANSWER + " " + i, category);
            cards.add(card);
        }

        return cards;
    }
}
