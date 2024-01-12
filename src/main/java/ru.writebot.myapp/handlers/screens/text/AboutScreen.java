package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;
import ru.writebot.myapp.utils.StringForScreenTextResponseCreate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AboutScreen implements ScreenHandler {

    private final ServiceButton serviceButton;

    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "О нас".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        // Устанавливаем экран в ответное сообщение
        response.setText(buildScreen().getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(buildScreen().getKeyboard())); // Установите клавиатуру
    }

    Screen buildScreen() {
        // Создаем экран
        return Screen.builder()
                .textOnScreen(StringForScreenTextResponseCreate.createTextForAboutScreen())
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
    }
}
