package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.utils.ScreenButtonsType;
import ru.writebot.myapp.utils.ScreenUtils;
import ru.writebot.myapp.utils.StringForScreenTextResponseCreate;

@Component
@RequiredArgsConstructor
public class MainScreen implements ScreenHandler {
    
    private final ScreenUtils screenUtils;

    @Override
    public boolean canHandle(Update update) {
        // Проверяем, содержит ли сообщение текст "Главная"
        return update.hasMessage() && "🔍Главная".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        // Создаем экран
        String textOnScreen = StringForScreenTextResponseCreate.createTextForMainScreen(update);
        Screen mainScreen =
                screenUtils.createScreenWithButtons(textOnScreen, ScreenButtonsType.SIMPLE.getTypeScreenButtons());
        // Устанавливаем экран в ответное сообщение
        response.setText(mainScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(mainScreen.getKeyboard())); // Установите клавиатуру
    }

}
