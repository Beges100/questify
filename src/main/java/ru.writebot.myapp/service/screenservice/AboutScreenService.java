package ru.writebot.myapp.service.screenservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.utils.ScreenButtonsType;
import ru.writebot.myapp.utils.ScreenUtils;
import ru.writebot.myapp.utils.StringForScreenTextResponseCreate;

@Service
@RequiredArgsConstructor
public class AboutScreenService {
    private final ScreenUtils screenUtils;

    public Screen buildAboutScreen() {
        return screenUtils.createScreenWithButtons(
                StringForScreenTextResponseCreate.createTextForAboutScreen(),
                ScreenButtonsType.SIMPLE.getTypeScreenButtons()
        );
    }
}
