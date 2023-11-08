package ru.writebot.myapp.entity;

public enum TaskLvl {
    EASY("Легко"),
    MEDIUM("Средне"),
    HARD("Сложно");

    private final String displayName;

    TaskLvl(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
