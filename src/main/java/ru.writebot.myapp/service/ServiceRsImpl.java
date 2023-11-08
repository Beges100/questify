package ru.writebot.myapp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ServiceRsImpl implements ServiceRs {
    @Override
    public void responseOnMainScreen(SendMessage sendMessage) {
        sendMessage.setText("Вы на главном экране");
    }
}
