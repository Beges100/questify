package ru.writebot.myapp.utils;

import java.util.List;
import java.util.Map;

/**
 *  Утильный класс, который требуется для определения кнопок на экранах
 * */
public enum ScreenButtonsType {
    SIMPLE(
            Map.of(
                    1, List.of("🔍Главная", "👤Профиль"),
                    2, List.of("F.A.Q.(disable)", "О нас"),
                    3, List.of("К заданиям")
            )
    ),

    RANDOM_VERIFICATION_SCREEN(
            Map.of(
                    1, List.of("🔍Главная", "👤Профиль"),
                    2, List.of("Мои друзья", "Найти друга"),
                    3, List.of("К заданиям")
            )
    );

    private final Map<Integer, List<String>> typeScreenButtons;

    ScreenButtonsType(Map<Integer, List<String>> TYPE_SCREEN_BUTTONS) {
        this.typeScreenButtons = TYPE_SCREEN_BUTTONS;
    }

    public Map<Integer, List<String>> getTypeScreenButtons() {
        return typeScreenButtons;
    }
}
