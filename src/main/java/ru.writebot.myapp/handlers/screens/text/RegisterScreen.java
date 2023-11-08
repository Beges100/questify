package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.entity.PersonalQualities;
import ru.writebot.myapp.entity.User;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;
import ru.writebot.myapp.service.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RegisterScreen implements ScreenHandler {

    private final ServiceButton serviceButton;
    private final UserServices userServices;

    @Override
    public boolean canHandle(Update update) {
        // Проверяем, содержит ли сообщение текст "Главная"
        return update.hasMessage() && "/start".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        String saved;
        if (!userServices.existsById(update.getMessage().getChatId())) {
            User user = new User();
            user.setCoins(15);
            user.setLevel(1);
            user.setExperience(0);
            PersonalQualities qualities = new PersonalQualities();
            user.setPersonalQualities(qualities);
            user.setFriends(new ArrayList<>());
            user.setId(update.getMessage().getChatId());
            user.setFirstName(update.getMessage().getFrom().getFirstName());
            userServices.saveUser(user);
            saved = "Вы успешно зарегистрировались";
        } else {
            saved = "Вы уже были зарегистрированы";
        }

        // Создаем экран
        Screen mainScreen = Screen.builder()
                .textOnScreen(saved)
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
