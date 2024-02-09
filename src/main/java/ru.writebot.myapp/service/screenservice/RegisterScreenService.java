package ru.writebot.myapp.service.screenservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.writebot.myapp.entity.PersonalQualities;
import ru.writebot.myapp.entity.User;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.UserServices;
import ru.writebot.myapp.utils.ScreenButtonsType;
import ru.writebot.myapp.utils.ScreenUtils;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class RegisterScreenService {

    private final ScreenUtils screenUtils;
    private final UserServices userServices;

    @Transactional
    public Screen registerUser(Update update) {
        if (!userServices.existsById(update.getMessage().getChatId())) {
            User user = createUserFromUpdate(update);
            userServices.saveUser(user);
            return screenUtils.createScreenWithButtons(
                    "Вы успешно зарегистрировались",
                    ScreenButtonsType.SIMPLE.getTypeScreenButtons()
            );
        } else {
            return screenUtils.createScreenWithButtons(
                    "Вы уже были зарегистрированы",
                    ScreenButtonsType.SIMPLE.getTypeScreenButtons()
            );
        }
    }

    private User createUserFromUpdate(Update update) {
        User user = new User();
        user.setCoins(15);
        user.setLevel(1);
        user.setExperience(0);
        PersonalQualities qualities = new PersonalQualities();
        user.setPersonalQualities(qualities);
        user.setFriends(new HashSet<>());
        user.setId(update.getMessage().getChatId());
        user.setFirstName(update.getMessage().getFrom().getFirstName());
        return user;
    }

}
