package ru.writebot.myapp.screens;


import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Builder
@Data
public class Screen {
    private String name;
    private String textOnScreen;
    private List<KeyboardRow> keyboard;
}
