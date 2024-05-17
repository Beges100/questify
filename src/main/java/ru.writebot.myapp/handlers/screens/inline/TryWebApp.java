package ru.writebot.myapp.handlers.screens.inline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppData;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.entity.User;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;
import ru.writebot.myapp.service.TaskServices;
import ru.writebot.myapp.service.UserServices;

import java.util.List;
import java.util.Map;

/**
 * Экран выбора задания
 **/
@Component
@RequiredArgsConstructor
public class TryWebApp implements InlineScreenHandler {
    private final ServiceButton serviceButton;

    @Override
    public boolean canHandle(Update update) {
        String callbackData = update.getCallbackQuery().getData();

        return callbackData.startsWith("open_");
    }

    @Override
    public void handle(Update update, SendMessage response) {
        // Создаем экран
        Screen mainScreen = Screen.builder()
                .textOnScreen("Huynya")
                .keyboard(
                        serviceButton.createKeyboard(
                                Map.of(
                                        1, List.of("🔍Главная")
                                )
                        )
                )
                .build();




        // Устанавливаем экран в ответное сообщение
        response.setText(mainScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(mainScreen.getKeyboard())); // Установите клавиатуру
    }
}
