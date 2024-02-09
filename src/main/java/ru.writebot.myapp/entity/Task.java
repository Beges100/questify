package ru.writebot.myapp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
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
                ✉Название: %s
                📝Описание: %s
                🪙Монет за задание: %s
                📈Опыт за задание: %s
                📂Категория: %s
                🔐Сложность: %s
                """,
                getName(),
                getDescription(),
                getCoinReward(),
                getExperienceReward(),
                getCategory().getDisplayName(),
                getTaskLvl().getDisplayName()
        );
    }

    public String toStringNameForOneTask() {
        return category.getIcon() + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return experienceReward == task.experienceReward && coinReward == task.coinReward && Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(description, task.description) && category == task.category && taskLvl == task.taskLvl;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, experienceReward, coinReward, category, taskLvl);
    }
}
