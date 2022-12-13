package ch.zhaw.card2brain.repository;

import ch.zhaw.card2brain.model.Category;
import ch.zhaw.card2brain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    List<Category> findCategoriesByOwner(User owner);


}
