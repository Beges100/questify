package ru.writebot.myapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "personal_qualities")
public class PersonalQualities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  Уровень общительности
     **/
    private int sociabilityLevel;
    /**
     *  Уровень дружелюбия
     **/
    private int friendlinessLevel;
    /**
     *  Уровень доброты
     **/
    private int kindnessLevel;
    /**
     *  Уровень открытости к новому
     **/
    private int opennessLevel;
    /**
     *  Другие качества, которые вы хотите включить
     **/
    @OneToOne(mappedBy = "personalQualities")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalQualities that = (PersonalQualities) o;
        return sociabilityLevel == that.sociabilityLevel && friendlinessLevel == that.friendlinessLevel && kindnessLevel == that.kindnessLevel && opennessLevel == that.opennessLevel && Objects.equals(id, that.id) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sociabilityLevel, friendlinessLevel, kindnessLevel, opennessLevel, user);
    }
}
