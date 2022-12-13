package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.dto.CategoryDto;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;

public interface CategoryService {


    CategoryDto addCategoryToUser(User user, Category category);

    CategoryDto getAllCategoriesToUser(User user);
}
