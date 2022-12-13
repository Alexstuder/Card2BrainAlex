package ch.zhaw.card2brain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Category")
public class Category extends BaseEntity {

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    @ManyToOne
    private User owner;

    public Category(String categoryName, User owner) {
        this.categoryName = categoryName;
        this.owner = owner;
    }

    public Category() {

    }
}
