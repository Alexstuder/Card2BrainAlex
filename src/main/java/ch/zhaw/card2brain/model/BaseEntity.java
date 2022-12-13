package ch.zhaw.card2brain.model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    @Column(name = "ID")
    protected Long id;

}
