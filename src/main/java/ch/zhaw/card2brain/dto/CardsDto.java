package ch.zhaw.card2brain.dto;

import ch.zhaw.card2brain.model.Card;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class CardsDto {

    @Getter
    @Setter
    @NonNull
    private User user;

    @Getter
    @Setter
    @NonNull
    private Category category;

    @Getter
    @Setter
    @NonNull
    private List<Card> cards = new ArrayList<>();

}
