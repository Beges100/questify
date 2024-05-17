package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendGame;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.service.TaskServices;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TryMakeWebAppScreen implements ScreenHandler {

    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "Открыть яндекс".equals(update.getMessage().getText());
    }

    //TODO рефаторинг, слишком перегруженный метод
    @Override
    public void handle(Update update, SendMessage response) {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();


        InlineKeyboardButton taskButton = new InlineKeyboardButton("Открыть яндекс");
//        taskButton.setCallbackData("open_yandex");
        taskButton.set
        taskButton.setWebApp(new WebAppInfo("https://www.bgoperator.ru/"));
        row.add(taskButton);

        keyboard.add(row);

        // Создаем объект ReplyKeyboardMarkup для инлайн-клавиатуры
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        response.setText("Надпись открыть яндекс:\n");

        response.setReplyMarkup(inlineKeyboardMarkup);
    }


}
