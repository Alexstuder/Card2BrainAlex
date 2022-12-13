package ch.zhaw.card2brain.services;

import ch.zhaw.card2brain.dto.UserDto;
import ch.zhaw.card2brain.model.User;
import ch.zhaw.card2brain.repository.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    DataAccess dataAccess;

    @Override
    public UserDto addUser(User user) {
        UserDto userDto = new UserDto(user);

        if (userExists(user)) {
            userDto.setMessage("User " + user.getUserName() + " exists already.");
        } else {
            dataAccess.getUserRepository().save(user);
            userDto.setMessage("User added in Database.");
        }
        return userDto;
    }

    @Override
    public UserDto delete(User user) {
        UserDto userDto = new UserDto(user);
        dataAccess.getUserRepository().delete(user);

        userDto.setMessage("User:" + user.getUserName() + " deleted.");
        return userDto;
    }


    @Override
    public boolean userExists(User user) {
        User exists = dataAccess.getUserRepository().getUserByMailAddress(user.getMailAddress());

        if (exists == null) {
            return false;
        } else {
            return true;
        }
    }

}
