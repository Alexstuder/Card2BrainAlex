package ch.zhaw.card2brain.dto;

import ch.zhaw.card2brain.model.User;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String mailAddress;

    @Getter
    @Setter
    private String message;

    public UserDto(User user) {
        this.setUserName(user.getUserName());
        this.setFirstName(user.getFirstName());
        this.setMailAddress(user.getMailAddress());

    }
}
