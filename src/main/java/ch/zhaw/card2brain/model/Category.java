package ch.zhaw.card2brain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "Category")
@NoArgsConstructor
@RequiredArgsConstructor
public class Category extends BaseEntity {

    @Getter
    @Setter
    @NonNull
    private String categoryName;

    @Getter
    @Setter
    @ManyToOne
    @NonNull
    private User owner;


}
