package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.dto.CategoryDto;
import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import ch.zhaw.card2brain.repository.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    private static final String EMPTY = "";
    @Autowired
    DataAccess dataAccess;
    @Autowired
    UserService userService;

    @Override
    public CategoryDto addCategoryToUser(User user, Category category) {
        CategoryDto categoryDto = new CategoryDto(user, category);

        if (userService.userExists(user)) {
            if (categoryExists(user, category)) {
                categoryDto.setMessage("Category" + category + " exists already.");
            } else {
                category.setOwner(user);
                // TODO : Alex : Was passiert , wenn die Category durch das Repository nicht angelegt werden konnte
                dataAccess.getCategoryRepository().save(category);
                categoryDto.setMessage("Category " + category.getCategoryName() + " has been added to user.");
            }
        } else {
            categoryDto.setMessage("Something went wrong : User :" + user.getUserName() + " does not exist.");
        }
        return categoryDto;
    }

    @Override
    public CategoryDto getAllCategoriesToUser(User user) {
        CategoryDto categoryDto = new CategoryDto();
        if (userService.userExists(user)) {
            categoryDto.setUser(user);
            categoryDto.setCategories(dataAccess.getCategoryRepository().findCategoriesByOwner(user));
            categoryDto.setMessage(EMPTY);
        } else {
            // TODO Alex : null als return Wert OK ?
            categoryDto.setMessage("Something went wrong : User does not exist.");
        }
        return categoryDto;
    }

    private boolean categoryExists(User user, Category category) {
        List<Category> exists = dataAccess.getCategoryRepository().findCategoriesByOwner(user);
        return exists.contains(category);
    }
}
