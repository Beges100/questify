package ru.writebot.myapp.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
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
}
