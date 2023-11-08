package ru.writebot.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServiceButtonImpl implements ServiceButton{
    public List<KeyboardRow> createKeyboard(Map<Integer, List<String>> buttonNames) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (Map.Entry<Integer, List<String>> entry : buttonNames.entrySet()) {
            List<String> names = entry.getValue();

            KeyboardRow keyboardRow = new KeyboardRow();
            for (String name : names) {
                keyboardRow.add(new KeyboardButton(name));
            }
            keyboard.add(keyboardRow);
        }

        return keyboard;
    }

}
