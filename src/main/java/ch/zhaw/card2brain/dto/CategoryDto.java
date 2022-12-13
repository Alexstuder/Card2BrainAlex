package ch.zhaw.card2brain.dto;

import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    @Getter
    @Setter
    User user;
    @Getter
    @Setter
    List<Category> categories;

    @Getter
    @Setter
    String message;

    public CategoryDto(User user, Category category) {
        this.user = user;
        this.categories = new ArrayList<>();
        this.categories.add(category);
        this.message = "";
    }

    public CategoryDto(User user) {
        this.user = user;
        this.categories = new ArrayList<>();
        this.message = "";
    }

    public CategoryDto() {
        this.user = new User();
        this.categories = new ArrayList<>();
        this.message = "";
    }

}
