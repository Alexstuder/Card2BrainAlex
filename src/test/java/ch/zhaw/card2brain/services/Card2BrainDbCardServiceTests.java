package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.TestDataGenerator.TestDataGenerator;
import ch.zhaw.card2brain.dto.CardsDto;
import ch.zhaw.card2brain.dto.CategoryDto;
import ch.zhaw.card2brain.model.Card;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import ch.zhaw.card2brain.repository.DataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Card2BrainDbCardServiceTests {

    private static final String MUTATION = "Mutation";
    private final DataAccess dataAccess;
    @Autowired
    CardService cardService;
    private User user;
    private Category category;


    @Autowired
    Card2BrainDbCardServiceTests(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @BeforeEach
    public void init() {
        dataAccess.getCardRepository().deleteAll();
        dataAccess.getCategoryRepository().deleteAll();
        dataAccess.getUserRepository().deleteAll();
    }

    @Test
    public void testGetAllCardsOfAUserToACategory() {
        //arrange
        //save test Cards on DB
        List<Card> cards = getInitalCards();
        dataAccess.getCardRepository().saveAll(cards);

        CategoryDto categoryDto = new CategoryDto(cards.get(0).getCategory().getOwner());
        List<Category> categories = new ArrayList<>();
        categories.add(cards.get(0).getCategory());
        categoryDto.setCategories(categories);

        //act
        CardsDto cardsDtoFromBackend = cardService.getAllCardsOfAUserToACategory(categoryDto);

        //assert
        assertEquals(50, cardsDtoFromBackend.getCards().size());
        assertEquals(category.hashCode(), cardsDtoFromBackend.getCategory().hashCode());
        assertEquals(user.hashCode(), cardsDtoFromBackend.getUser().hashCode());
    }

    @Test
    void testAddCards() {
        //arrange
        List<Card> cards = getInitalCards();
        //act
        cardService.addCards(cards);

        //assert
        List<Category> categories = dataAccess.getCategoryRepository().findCategoriesByOwner(user);
        // as long as we test with only 1 category ; get(0) feels ok.
        List<Card> resultCards = dataAccess.getCardRepository().findCardByCategory(categories.get(0));
        assertEquals(resultCards.size(), cards.size());

        assertTrue(cardListsAreEqual(cards, resultCards));

    }

    private boolean cardListsAreEqual(List<Card> cards, List<Card> resultCards) {
        int counter = 0;
        for (Card resultCard : resultCards) {

            for (Card card : cards) {
                if (Objects.equals(resultCard.getId(), card.getId())) {
                    if (!resultCard.getAnswer().equals(card.getAnswer())
                            || !resultCard.getQuestion().equals(card.getQuestion())
                            || !resultCard.getAnsweredLastTime().equals(card.getAnsweredLastTime())
                            || !(resultCard.getCounterRight() == card.getCounterRight())
                            || !(resultCard.getCounterFalse() == card.getCounterFalse())
                            || !(resultCard.getCategory().getCategoryName().equals(card.getCategory().getCategoryName()))
                            || !(resultCard.getCategory().getOwner().getUserName().equals(card.getCategory().getOwner().getUserName()))
                            || !(resultCard.getCategory().getOwner().getMailAddress().equals(card.getCategory().getOwner().getMailAddress()))) {
                        return false;
                    } else {
                        counter++;
                    }
                }
            }
        }
        return counter == cards.size();
    }

    @Test
    public void testUpdateCards() {

        //arrange
        //save test Cards on DB
        List<Card> cards = getInitalCards();
        dataAccess.getCardRepository().saveAll(cards);

        //get some mutations
        List<Card> mutationOnCards = getMutationOnCards(cards);

        User user = cards.get(0).getCategory().getOwner();
        Category category = cards.get(0).getCategory();
        CardsDto mutationDto = new CardsDto(user, category);

        // get the Dto from the mutated cards
        mutationDto.setCards(mutationOnCards);

        //make sure there are 50 cards saved
        assertEquals(50, dataAccess.getCardRepository().findCardByCategory(category).size());
        //act
        cardService.updateCardsToCategorie(mutationDto);

        //assert
        //make sure there are still 50 cards saved and not 100 !
        List<Card> cardsFromDbAfterMutation = dataAccess.getCardRepository().findCardByCategory(category);
        assertEquals(50, cardsFromDbAfterMutation.size());

        assertTrue(findMutatedCardFromDB(cardsFromDbAfterMutation));
    }

    @Test
    public void testDeleteCards() {
        //arrange
        //save test Cards on DB
        List<Card> cards = getInitalCards();
        dataAccess.getCardRepository().saveAll(cards);

        List<Card> cardsToDelete = new ArrayList<>();
        cardsToDelete.add(cards.get(10));
        cardsToDelete.add(cards.get(20));

        CardsDto cardsDtoToDelete = new CardsDto(cards.get(0).getCategory().getOwner(), cards.get(0).getCategory());
        cardsDtoToDelete.getCards().addAll(cardsToDelete);

        //act
        cardService.deleteCards(cardsDtoToDelete);
        //assert
        assertTrue(rightCardsExistOnDB(cardsToDelete, cards));

    }

    private boolean rightCardsExistOnDB(List<Card> cardsToDelete, List<Card> cards) {

        int counterExistsOnDb = 0;
        int counterDeleted = 0;
        for (Card card : cards) {
            if (dataAccess.getCardRepository().existsById(card.getId())) {
                counterExistsOnDb++;
            } else {
                for (Card deletedCard : cardsToDelete) {
                    if (card.getId().equals(deletedCard.getId())) {
                        counterDeleted++;
                    }
                }
            }
        }
        return counterExistsOnDb == 48 && counterDeleted == 2;
    }

    private boolean findMutatedCardFromDB(List<Card> findMutatedDataInCards) {

        int counter = 0;
        for (Card card : findMutatedDataInCards) {
            if (card.getAnswer().contains(MUTATION)) counter++;
            if (card.getQuestion().contains(MUTATION)) counter++;
        }
        return counter == 2;
    }

    private List<Card> getMutationOnCards(List<Card> cards) {
        // Get some mutation on Cards
        cards.get(10).setAnswer(MUTATION);
        cards.get(20).setQuestion(MUTATION);
        return cards;
    }

    private List<Card> getInitalCards() {
        user = TestDataGenerator.GET_DEFAULT_USER();
        dataAccess.getUserRepository().save(user);

        category = TestDataGenerator.GET_DEFAULT_CATEGORY_TO_USER(user);
        dataAccess.getCategoryRepository().save(category);

        return TestDataGenerator.GET_TEST_CARDS(category);

    }
}
