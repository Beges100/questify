package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.screenservice.AboutScreenService;

@Component
@RequiredArgsConstructor
public class AboutScreen implements ScreenHandler {
    private final AboutScreenService aboutScreenService;

    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "О нас".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        Screen aboutScreen = aboutScreenService.buildAboutScreen();
        // Устанавливаем экран в ответное сообщение
        response.setText(aboutScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(aboutScreen.getKeyboard())); // Установите клавиатуру
    }

}
