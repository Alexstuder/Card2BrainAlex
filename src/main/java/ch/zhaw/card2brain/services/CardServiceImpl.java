package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.dto.CardsDto;
import ch.zhaw.card2brain.dto.CategoryDto;
import ch.zhaw.card2brain.model.Card;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import ch.zhaw.card2brain.repository.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardServiceImpl implements CardService {

    @Autowired
    DataAccess dataAccess;

    @Override
    public CardsDto getAllCardsOfAUserToACategory(CategoryDto categoryDto) {
        CardsDto cardsDto = new CardsDto(categoryDto.getCategories().get(0).getOwner(), categoryDto.getCategories().get(0));
        cardsDto.getCards().addAll(dataAccess.getCardRepository().findCardByCategory(categoryDto.getCategories().get(0)));
        return cardsDto;
    }

    @Override
    public void addCards(List<Card> cards) {
        dataAccess.getCardRepository().saveAll(cards);
    }

    @Override
    public void updateCardsToCategorie(CardsDto cardsDto) {
        List<Card> cards = new ArrayList<>();

        User user = cardsDto.getCategory().getOwner();
        Category category = cardsDto.getCategory();

        dataAccess.getCardRepository().saveAll(cardsDto.getCards());
    }

    @Override
    public void deleteCards(CardsDto cardsDto) {
        dataAccess.getCardRepository().deleteAll(cardsDto.getCards());
    }
}
