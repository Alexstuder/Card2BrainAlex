package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.dto.UserDto;
import ch.zhaw.card2brain.model.User;

public interface UserService {

    UserDto addUser(User user);

    UserDto delete(User user);

    boolean userExists(User user);

}
