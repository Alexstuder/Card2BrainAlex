package ch.zhaw.card2brain.repository;

import ch.zhaw.card2brain.model.Card;
import ch.zhaw.card2brain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {


    List<Card> findCardByCategory(Category category);

}
