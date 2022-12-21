package ch.zhaw.card2brain.dto;

import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto extends UserDto {
    @Getter
    @Setter
    List<Category> categories = new ArrayList<>();

    @Getter
    @Setter
    String message = "";

    public CategoryDto(User user) {
        super(user);
    }

}
