package ru.writebot.myapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "task")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int experienceReward;
    private int coinReward;

    @Enumerated(EnumType.STRING)
    private TaskCategory category;
    @Enumerated(EnumType.STRING)
    private TaskLvl taskLvl;

    public Task(String name, String description, int experienceReward, int coinReward, TaskCategory category) {
        this.name = name;
        this.description = description;
        this.experienceReward = experienceReward;
        this.coinReward = coinReward;
        this.category = category;
    }

    public String toStringForOneTask() {
        return String.format("""
                Название: %s
                Описание: %s
                Монет за задание: %s
                Опыт за задание: %s
                Категория: %s
                Сложность: %s
                """,
                getName(),
                getDescription(),
                getCoinReward(),
                getExperienceReward(),
                getCategory().getDisplayName(),
                getTaskLvl().getDisplayName()
        );


    }
}
