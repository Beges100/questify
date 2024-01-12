package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.entity.User;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;
import ru.writebot.myapp.service.UserServices;
import ru.writebot.myapp.utils.StringForScreenTextResponseCreate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProfileScreen implements ScreenHandler {
    private final ServiceButton serviceButton;
    private final UserServices userServices;

    @Override
    public boolean canHandle(Update update) {
        // Проверяем, содержит ли сообщение текст "Главная"
        return update.hasMessage() && "👤Профиль".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        User user = userServices.getUserById(update.getMessage().getChatId());

        // Создаем экран
        Screen mainScreen = Screen.builder()
                .textOnScreen(StringForScreenTextResponseCreate.createTextForProfileScreen(user))
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
