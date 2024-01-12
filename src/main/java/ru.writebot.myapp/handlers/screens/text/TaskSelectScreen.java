package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.service.ServiceButton;
import ru.writebot.myapp.service.TaskServices;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskSelectScreen implements ScreenHandler {
    private final ServiceButton serviceButton;
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
        //TODO рефакторинг, убрать костыльный сетт кнопок во избежании NPE или index out of bound exception
        InlineKeyboardButton task1 = new InlineKeyboardButton(threeRandomTasks.get(0).getCategory().getIcon());
        task1.setCallbackData("task_" + threeRandomTasks.get(0).getId());

        InlineKeyboardButton task2 = new InlineKeyboardButton(threeRandomTasks.get(1).getCategory().getIcon());
        task2.setCallbackData("task_" + threeRandomTasks.get(1).getId());

        InlineKeyboardButton task3 = new InlineKeyboardButton(threeRandomTasks.get(2).getCategory().getIcon());
        task3.setCallbackData("task_" + threeRandomTasks.get(2).getId());

        row.add(task1);
        row.add(task2);
        row.add(task3);

        keyboard.add(row);

// Создаем объект ReplyKeyboardMarkup для инлайн-клавиатуры
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        response.setText("Выберите одно из трех заданий:\n" + sb);

        response.setReplyMarkup(inlineKeyboardMarkup);
    }
}
