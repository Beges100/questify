package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.screenservice.RegisterScreenService;

@Component
@RequiredArgsConstructor
public class RegisterScreen implements ScreenHandler {

    private final RegisterScreenService registerScreenService;

    @Override
    public boolean canHandle(Update update) {
        // Проверяем, содержит ли сообщение текст "Главная"
        return update.hasMessage() && "/start".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        Screen screen = registerScreenService.registerUser(update);

        // Устанавливаем экран в ответное сообщение
        response.setText(screen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(screen.getKeyboard())); // Установите клавиатуру
    }
}
