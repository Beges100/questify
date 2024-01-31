package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.screenservice.RandomVerificationTasksScreenService;

@Component
@RequiredArgsConstructor
public class RandomVerificationTasksScreen implements ScreenHandler {
    private final RandomVerificationTasksScreenService screenService;

    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "Случайные задания на проверке".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        Screen mainScreen = screenService.getRandomVerificationTasksScreen();

        response.setText(mainScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(mainScreen.getKeyboard()));
    }
}
