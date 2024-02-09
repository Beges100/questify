package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.entity.User;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.UserServices;
import ru.writebot.myapp.utils.ScreenButtonsType;
import ru.writebot.myapp.utils.ScreenUtils;
import ru.writebot.myapp.utils.StringForScreenTextResponseCreate;

@Component
@RequiredArgsConstructor
public class ProfileScreen implements ScreenHandler {
    private final UserServices userServices;
    private final ScreenUtils screenUtils;

    @Override
    public boolean canHandle(Update update) {
        // Проверяем, содержит ли сообщение текст "Главная"
        return update.hasMessage() && "👤Профиль".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        User user = userServices.getUserById(update.getMessage().getChatId());

        // Создаем экран
        Screen mainScreen = screenUtils.createScreenWithButtons(
                StringForScreenTextResponseCreate.createTextForProfileScreen(user),
                ScreenButtonsType.SIMPLE.getTypeScreenButtons()
        );

        // Устанавливаем экран в ответное сообщение
        response.setText(mainScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(mainScreen.getKeyboard())); // Установите клавиатуру
    }
}
