package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;
import ru.writebot.myapp.service.TaskServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskSelectScreen implements ScreenHandler {
    private final ServiceButton serviceButton;
    private final TaskServices taskServices;

    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "Выбрать задание".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {


        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton task1 = new InlineKeyboardButton("1");
        task1.setCallbackData("task_" + 1);

        InlineKeyboardButton task2 = new InlineKeyboardButton("2");
        task2.setCallbackData("task_2");

        InlineKeyboardButton task3 = new InlineKeyboardButton("3");
        task3.setCallbackData("task_3");

        row.add(task1);
        row.add(task2);
        row.add(task3);

        keyboard.add(row);

// Создаем объект ReplyKeyboardMarkup для инлайн-клавиатуры
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        response.setText("Выберите одно из трех заданий по ID:\n" +
                "1 Попробуйте новый вид искусства \n" +
                "2 Прогуляйтесь 5 км\n" +
                "3 Прочитайте 10 страниц в книге");

        response.setReplyMarkup(inlineKeyboardMarkup);

/*
        Task taskById1 = taskServices.getTaskById(2L);
        Task taskById2 = taskServices.getTaskById(3L);
        Task taskById3 = taskServices.getTaskById(4L);

        String mainScreenText = String.format("""
                Выберите одно из трех заданий по ID:
                %d. %s
                %d. %s
                %d. %s

                """,
                taskById1.getId(), taskById1.getName(),
                taskById2.getId(), taskById2.getName(),
                taskById3.getId(), taskById3.getName());

        // Создаем экран
        Screen mainScreen = Screen.builder()
                .textOnScreen(mainScreenText)
                .keyboard(
                        serviceButton.createKeyboard(
                                Map.of(
                                        1, List.of("🔍Главная"),
                                        2, List.of("Выбрать " + taskById1.getId().toString(), "Выбрать " + taskById2.getId().toString()),
                                        3, List.of("Выбрать " + taskById3.getId().toString())
                                )
                        )
                )
                .build();

        // Устанавливаем экран в ответное сообщение
        response.setText(mainScreen.getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(mainScreen.getKeyboard())); // Установите клавиатуру
*/
    }
}
