package ch.zhaw.card2brain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Card")
@NoArgsConstructor
@RequiredArgsConstructor
public class Card extends BaseEntity {

    @Getter
    @Setter
    @NonNull
    private String question;

    @Getter
    @Setter
    @NonNull
    private String answer;

    @Getter
    @Setter
    private int counterRight = 0;

    @Getter
    @Setter
    private int counterFalse = 0;

    @Getter
    @Setter
    private LocalDateTime answeredLastTime = LocalDateTime.of(1970, 01, 01, 00, 00, 00);

    @Getter
    @ManyToOne
    @NonNull
    private Category category;


}
