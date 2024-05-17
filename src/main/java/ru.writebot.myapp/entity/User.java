package ru.writebot.myapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
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
    private Set<Task> currentTasks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_achievements",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id")
    )
    private Set<Achievement> achievements = new HashSet<>();

    /**
     * Список друзей пользователя
     **/
    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(level, user.level) && Objects.equals(experience, user.experience) && Objects.equals(coins, user.coins) && Objects.equals(currentTasks, user.currentTasks) && Objects.equals(achievements, user.achievements) && Objects.equals(friends, user.friends) && Objects.equals(personalQualities, user.personalQualities) && Objects.equals(completedTasks, user.completedTasks) && Objects.equals(categoryLevels, user.categoryLevels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, level, experience, coins, currentTasks, achievements, friends, personalQualities, completedTasks, categoryLevels);
    }
}