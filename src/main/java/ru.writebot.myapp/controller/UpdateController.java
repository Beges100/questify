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

        inlineScreenHandlers.stream()
                .filter(handler -> handler.canHandle(update))
                .findFirst()
                .ifPresent(handler -> handler.handle(update, sendMessage));

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

        messageHandlers.stream()
                .filter(handler -> handler.canHandle(update))
                .findFirst()
                .ifPresent(handler -> handler.handle(update, sendMessage));

        if (sendMessage.getText() == null) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Раздел в разработке");
        }

        setView(sendMessage);
        LOGGER.info(String.format("Пользователь с ID: %s, написал в чат: %s%n", update.getMessage().getChatId().toString(), update.getMessage().getText()));
    }

    public void setView(SendMessage sendMessage) {
        initBot.sendAnswerMessage(sendMessage);
    }
}