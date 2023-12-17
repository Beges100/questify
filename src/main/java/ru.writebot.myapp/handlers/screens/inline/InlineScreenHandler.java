package ru.writebot.myapp.handlers.screens.inline;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface InlineScreenHandler {

    /**
     * Проверяет, может ли данный обработчик обработать сообщение из обновления (Update).
     *
     * @param update обновление, содержащее сообщение от пользователя
     * @return true, если обработчик способен обработать сообщение, иначе false
     */
    boolean canHandle(Update update);

    /**
     * Обрабатывает сообщение из обновления (Update) и создает ответное сообщение.
     *
     * @param update       обновление, содержащее сообщение от пользователя
     * @param response     ответное сообщение, которое будет отправлено пользователю
     */
    void handle(Update update, SendMessage response);
}
