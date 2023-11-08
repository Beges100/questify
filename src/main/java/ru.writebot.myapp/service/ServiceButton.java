package ru.writebot.myapp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;
import java.util.Map;

public interface ServiceButton {

    List<KeyboardRow> createKeyboard(Map<Integer, List<String>> buttonNames);
}
