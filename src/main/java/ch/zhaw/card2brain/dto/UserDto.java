package ch.zhaw.card2brain.dto;

import ch.zhaw.card2brain.model.User;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    @Getter
    @Setter
    @NonNull
    private String userName;

    @Getter
    @Setter
    @NonNull
    private String firstName;

    @Getter
    @Setter
    @NonNull
    private String mailAddress;

    @Getter
    @Setter
    private String message;

    public UserDto(User user) {
        this(user.getUserName(), user.getFirstName(), user.getMailAddress());
    }
}
