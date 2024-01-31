package ru.writebot.myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.writebot.myapp.config.InitBot;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.handlers.screens.inline.InlineScreenHandler;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateController.class);

    private InitBot initBot;
    private final List<ScreenHandler> messageHandlers;
    private final List<InlineScreenHandler> inlineScreenHandlers;

    public void registerBot(InitBot initBot) {
        this.initBot = initBot;
    }

    public void processUpdate(Update update) {
        if (update.hasMessage()) {
            sendMessage(update);
        } else if (update.hasCallbackQuery()) {
            inlineSendMessage(update);
        }
    }

    /**
     * Метод для обработки сообщений с типом клавиатуры inline
     **/
    @SneakyThrows
    public void inlineSendMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());

        for (InlineScreenHandler handler : inlineScreenHandlers) {
            if (handler.canHandle(update)) {
                handler.handle(update, sendMessage);
                break; // Останавливаем цикл, так как сообщение обработано
            }
        }

        setView(sendMessage);
        LOGGER.info(String.format("Пользователь с ID: %s, написал в чат: %s%n", update.getMessage().getChatId().toString(), update.getMessage().getText()));
    }

    /**
     * Метод для обработки сообщений с типом клавиатуры text
     **/
    @SneakyThrows
    public void sendMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        for (ScreenHandler handler : messageHandlers) {
            if (handler.canHandle(update)) {
                handler.handle(update, sendMessage);
                break; // Останавливаем цикл, так как сообщение обработано
            }
        }
        if (sendMessage.getText() == null) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Раздел в разработке");
        }
//6129730549 второй акк
        setView(sendMessage);
        LOGGER.info(String.format("Пользователь с ID: %s, написал в чат: %s%n", update.getMessage().getChatId().toString(), update.getMessage().getText()));
    }

    public void setView(SendMessage sendMessage) {
        initBot.sendAnswerMessage(sendMessage);
    }
}