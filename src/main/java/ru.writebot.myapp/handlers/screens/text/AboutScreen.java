package ru.writebot.myapp.handlers.screens.text;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.writebot.myapp.handlers.ScreenHandler;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AboutScreen implements ScreenHandler {

    private final ServiceButton serviceButton;

    @Override
    public boolean canHandle(Update update) {
        return update.hasMessage() && "О нас".equals(update.getMessage().getText());
    }

    @Override
    public void handle(Update update, SendMessage response) {
        // Устанавливаем экран в ответное сообщение
        response.setText(buildScreen().getTextOnScreen());
        response.setReplyMarkup(new ReplyKeyboardMarkup(buildScreen().getKeyboard())); // Установите клавиатуру
    }

    Screen buildScreen() {
        String mainScreenText =
                "Questify: Преврати жизнь в квест\n\n" +
                        "Описание:\n" +
                        "Questify - это увлекательная игра в чат-боте Telegram, которая предлагает игрокам превратить свою повседневную жизнь в увлекательный квест. " +
                        "Игроки выполняют реальные задания в различных сферах жизни, таких как искусство, спорт, путешествия и другие, и зарабатывают опыт и монеты, " +
                        "развивая своего персонажа в игре. Игра также предоставляет возможность вызывать друзей на выполнение заданий и получать дополнительные бонусы. " +
                        "Уровни, достижения и статистика делают игру увлекательной и мотивирующей. Questify - это путешествие, на котором каждый шаг приближает " +
                        "вас к новому достижению и увлекательным приключениям в реальной жизни.";

        // Создаем экран

        return Screen.builder()
                .textOnScreen(mainScreenText)
                .keyboard(
                        serviceButton.createKeyboard(
                                Map.of(
                                        1, List.of("🔍Главная", "👤Профиль"),
                                        2, List.of("F.A.Q.(disable)", "О нас"),
                                        3, List.of("К заданиям")
                                )
                        )
                )
                .build();
    }
}
