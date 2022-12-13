package ch.zhaw.card2brain.repository;

import ch.zhaw.card2brain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByMailAddress(String mailAddress);
}
