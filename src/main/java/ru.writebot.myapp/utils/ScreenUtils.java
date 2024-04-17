package ru.writebot.myapp.utils;

import org.springframework.stereotype.Component;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;

import java.util.List;
import java.util.Map;

@Component
public class ScreenUtils {
    private final ServiceButton serviceButton;

    public ScreenUtils(ServiceButton serviceButton) {
        this.serviceButton = serviceButton;
    }

    public Screen createScreenWithButtons(String text, Map<Integer, List<String>> buttonNames) {
        return Screen.builder()
                .textOnScreen(text)
                .keyboard(serviceButton.createKeyboard(buttonNames))
                .build();
    }
}
