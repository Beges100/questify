package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MainScreen implements ScreenHandler {

    private final ServiceButton serviceButton;

    @Override
    public boolean canHandle(Update update) {
        // Проверяем, содержит ли сообщение текст "Главная"
        return update.hasMessage() && "🔍Главная".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        String mainScreenText = String.format(""" 
                НА ДАННЫЙ МОМЕНТ БОТ НАХОДИТСЯ НА СТАДИИ РАЗРАБОТКИ
                По предложениям и багам писать @beges56
                
                🔍 Главное меню\n
                👤 Профиль %s\n
                📊 Уровень: %d\n
                💡 Опыт: %d/500\n
                🏆 Достижения: %d\n
                📌 Баланс Монет: %d\n
                👥 Вызовите друга на выполнение задания и получите +20%% опыта!""", update.getMessage().getFrom().getFirstName(), 5, 15, 12, 200);

        // Создаем экран
        Screen mainScreen = Screen.builder()
                .textOnScreen(mainScreenText)
                .keyboard(
                        serviceButton.createKeyboard(
                                Map.of(
                                        1, List.of("🔍Главная", "👤Профиль"),
                                        2, List.of("F.A.Q.(disable)", "О нас"),
                                        3, List.of("К заданиям")
                                )
                        )
                )
                .build();

        // Устанавливаем экран в ответное сообщение
        response.setText(mainScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(mainScreen.getKeyboard())); // Установите клавиатуру
    }
}
