package ru.writebot.myapp.entity;

public enum TaskCategory {
    ART("Искусство"),
    SPORT("Спорт"),
    TRAVEL("Путешествия"),
    HOBBY("Хобби"),
    ENTERTAINMENT("Развлечения"),
    OTHER("Другое");

    private final String displayName;

    TaskCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
