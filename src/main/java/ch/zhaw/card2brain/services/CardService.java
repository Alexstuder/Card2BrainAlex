package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.dto.CardsDto;
import ch.zhaw.card2brain.dto.CategoryDto;
import ch.zhaw.card2brain.model.Card;

import java.util.List;

public interface CardService {


    CardsDto getAllCardsOfAUserToACategory(CategoryDto categoryDto);

    void addCards(List<Card> cards);

    void updateCardsToCategorie(CardsDto cardsDto);

    void deleteCards(CardsDto cardsDto);

}
