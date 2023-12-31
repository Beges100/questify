package ru.writebot.myapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "\"user\"")
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    @Column(nullable = false)
    private String firstName;
    private Integer level;
    private Integer experience;
    private Integer coins;

    /**
     * Текущие задачи для пользователя
     **/
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_current_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> currentTasks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_achievements",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id")
    )
    private List<Achievement> achievements = new ArrayList<>();

    /**
     * Список друзей пользователя
     **/
    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends = new ArrayList<>();

    /**
     * Персональные качества пользователя (Общительность, дружелюбие и тд)
     **/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_qualities_id")
    private PersonalQualities personalQualities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_completed_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> completedTasks = new HashSet<>();

    /**
     * Мапа которая собирает уровни прокачки каждой категории
     **/
    @ElementCollection
    @CollectionTable(name = "user_category_levels", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyEnumerated(EnumType.STRING) // Указывает, что ключами являются перечисления
    @Column(name = "category_level") // Название колонки для значений
    private Map<TaskCategory, Integer> categoryLevels = new HashMap<>();

    public User(String firstName) {
        this.firstName = firstName;
        this.personalQualities = new PersonalQualities();
        // Инициализируем уровни для всех категорий
        for (TaskCategory category : TaskCategory.values()) {
            categoryLevels.put(category, 1); // Начальный уровень для каждой категории
        }
    }
}