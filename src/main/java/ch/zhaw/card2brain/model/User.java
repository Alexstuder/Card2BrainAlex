package ch.zhaw.card2brain.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Card2Brain_User")
public class User extends BaseEntity {

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String mailAddress;


}
