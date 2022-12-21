package ch.zhaw.card2brain.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "Card2Brain_User")
@RequiredArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

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


}
