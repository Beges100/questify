package ru.writebot.myapp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ServiceRs {

    void responseOnMainScreen(SendMessage sendMessage);

}
