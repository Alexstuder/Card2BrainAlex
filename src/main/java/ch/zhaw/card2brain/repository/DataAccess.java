package ch.zhaw.card2brain.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataAccess {

    @Getter
    private final UserRepository userRepository;
    @Getter
    private final CategoryRepository categoryRepository;

    @Getter
    private final CardRepository cardRepository;

    @Autowired
    public DataAccess(UserRepository userRepository, CategoryRepository categoryRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.cardRepository = cardRepository;
    }

}
