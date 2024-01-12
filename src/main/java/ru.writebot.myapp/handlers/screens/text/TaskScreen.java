package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.entity.User;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;
import ru.writebot.myapp.service.UserServices;
import ru.writebot.myapp.utils.StringForScreenTextResponseCreate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskScreen implements ScreenHandler {
    private final ServiceButton serviceButton;
    private final UserServices userServices;
    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "К заданиям".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        User userById = userServices.getUserById(update.getMessage().getChatId());
        StringBuilder sb = new StringBuilder();

        userById.getCurrentTasks().forEach(a -> sb.append("\n" + a.getCategory().getIcon() + a.getName() + "\n"));

//        userById.getCurrentTasks().forEach(a -> sb.append("\n" + a.toStringForOneTask() + "\n"));
        String taskFromUser = userById.getCurrentTasks().isEmpty() ? "Нет активных заданий" :
                sb.toString();

        // Создаем экран
        Screen mainScreen = Screen.builder()
                .textOnScreen(StringForScreenTextResponseCreate.createTextForTaskScreen(taskFromUser))
                .keyboard(
                        serviceButton.createKeyboard(
                                Map.of(
                                        1, List.of("🔍Главная", "👤Профиль"),
                                        2, List.of("Мои друзья", "Найти друга"),
                                        3, List.of("Выбрать задание")
                                )
                        )
                )
                .build();

        // Устанавливаем экран в ответное сообщение
        response.setText(mainScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(mainScreen.getKeyboard())); // Установите клавиатуру
    }
}
