package ch.zhaw.card2brain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Card")
public class Card extends BaseEntity {

    @Getter
    @Setter
    private String question;

    @Getter
    @Setter
    private String answer;

    @Getter
    @Setter
    private int counterRight;

    @Getter
    @Setter
    private int counterFalse;

    @Getter
    @Setter
    private LocalDateTime answeredLastTime;

    @Getter
    @ManyToOne
    private Category category;

    public Card(Category category, String answer, String question) {
        this.category = category;
        this.answer = answer;
        this.question = question;
    }

    public Card() {

    }
}
