package ru.writebot.myapp.entity;

public enum TaskCategory {
    ART("Искусство", "\uD83C\uDFA8"),
    SPORT("Спорт", "⚽"),
    TRAVEL("Путешествия", "✈"),
    HOBBY("Хобби", "\uD83C\uDFB2"),
    ENTERTAINMENT("Развлечения", "\uD83C\uDF89"),
    OTHER("Другое", "\uD83D\uDC88");

    private final String displayName;
    private final String icon;

    TaskCategory(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIcon() {
        return icon;
    }
}
