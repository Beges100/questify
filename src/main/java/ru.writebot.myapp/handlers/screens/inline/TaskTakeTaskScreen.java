package ru.writebot.myapp.handlers.screens.inline;

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
import ru.writebot.myapp.service.TaskServices;
import ru.writebot.myapp.service.UserServices;

import java.util.List;
import java.util.Map;

/**
 * Экран выбора задания
 **/
@Component
@RequiredArgsConstructor
public class TaskTakeTaskScreen implements InlineScreenHandler {
    private final ServiceButton serviceButton;
    private final TaskServices taskServices;
    private final UserServices userServices;

    @Override
    public boolean canHandle(Update update) {
        String callbackData = update.getCallbackQuery().getData();

        return callbackData.startsWith("task_");
    }

    @Override
    public void handle(Update update, SendMessage response) {
        String taskId = update.getCallbackQuery().getData().replaceFirst("task_", "");

        Long id = Long.valueOf(taskId);
        User userById = userServices.getUserById(update.getCallbackQuery().getMessage().getChatId());
        String mainScreenText;
        Task taskById = taskServices.getTaskById(id);
        if (!userById.getCurrentTasks().contains(taskById)) {
            userById.getCurrentTasks().add(taskById);
            userServices.saveUser(userById);

            mainScreenText = String.format(""" 
                Вы выбрали задание
                %s
                """, taskById != null ? taskById.getName() : "Такого задания не существует"
            );
        } else {
            mainScreenText = "Вы уже брали это задание";
        }


        // Создаем экран
        Screen mainScreen = Screen.builder()
                .textOnScreen(mainScreenText)
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
