package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.service.TaskServices;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskSelectScreen implements ScreenHandler {
    private final TaskServices taskServices;

    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "Выбрать задание".equals(update.getMessage().getText());
    }

    //TODO рефаторинг, сделать SingleResponsibility
    @Override
    public void handle(Update update, SendMessage response) {
        StringBuilder sb = new StringBuilder();
        List<Task> threeRandomTasks = taskServices.getThreeRandomTasks();
        threeRandomTasks.forEach(task -> sb.append(task.toStringNameForOneTask()).append("\n"));

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        threeRandomTasks.forEach(task -> {
            InlineKeyboardButton taskButton = new InlineKeyboardButton(threeRandomTasks.get(0).getCategory().getIcon());
            taskButton.setCallbackData("task_" + threeRandomTasks.get(0).getId());
            row.add(taskButton);
        });

        keyboard.add(row);

        // Создаем объект ReplyKeyboardMarkup для инлайн-клавиатуры
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        response.setText("Выберите одно из трех заданий:\n" + sb);

        response.setReplyMarkup(inlineKeyboardMarkup);
    }


}
