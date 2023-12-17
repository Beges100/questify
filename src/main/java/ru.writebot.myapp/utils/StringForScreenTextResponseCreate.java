package ru.writebot.myapp.utils;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.writebot.myapp.entity.User;

import javax.validation.constraints.NotNull;

public final class StringForScreenTextResponseCreate {

    public static String createTextForProfileScreen(@NotNull User user) {
        return String.format("""
                👤Questify: Профиль %s \n
                
                🏆 Уровень: %d
                💡 Опыт: %d
                🏅 Достижения: %d
                📌 Монеты: %d
                                                                               
                📊 Статистика:
                  - Заданий выполнено: %d
                  - Друзей в игре: 0
                  - Бонус опыта: +20%%
                         
                🦾 Личные качества:
                    - Уровень дружелюбия: %d
                    - Уровень доброты: %d
                    - Уровень общительности: %d   
                    - Уровень открытости к новому: %d 
                                                                               
                👉 [Изменить Профиль]
                                                                               
                👥 Вызовите друга на выполнение задания и получите +20%% опыта!""",
                user.getFirstName(),
                user.getLevel(),
                user.getExperience(),
                user.getAchievements() != null ? user.getAchievements().size() : 0,
                user.getCoins(),
                user.getCompletedTasks() != null ? user.getCompletedTasks().size() : 0,
                user.getPersonalQualities().getFriendlinessLevel(),
                user.getPersonalQualities().getKindnessLevel(),
                user.getPersonalQualities().getSociabilityLevel(),
                user.getPersonalQualities().getOpennessLevel()
        );
    }

    public static String createTextForMainScreen(@NotNull Update update) {
        return String.format(""" 
                НА ДАННЫЙ МОМЕНТ БОТ НАХОДИТСЯ НА СТАДИИ РАЗРАБОТКИ
                По предложениям и багам писать @beges56
                
                🔍 Главное меню\n
                👤 Профиль %s\n
                📊 Уровень: %d\n
                💡 Опыт: %d/500\n
                🏆 Достижения: %d\n
                📌 Баланс Монет: %d\n
                👥 Вызовите друга на выполнение задания и получите +20%% опыта!""", update.getMessage().getFrom().getFirstName(), 5, 15, 12, 200);
    }

    public static String createTextForAboutScreen() {
        return "Questify: Преврати жизнь в квест\n\n" +
                "Описание:\n" +
                "Questify - это увлекательная игра в чат-боте Telegram, которая предлагает игрокам превратить свою повседневную жизнь в увлекательный квест. " +
                "Игроки выполняют реальные задания в различных сферах жизни, таких как искусство, спорт, путешествия и другие, и зарабатывают опыт и монеты, " +
                "развивая своего персонажа в игре. Игра также предоставляет возможность вызывать друзей на выполнение заданий и получать дополнительные бонусы. " +
                "Уровни, достижения и статистика делают игру увлекательной и мотивирующей. Questify - это путешествие, на котором каждый шаг приближает " +
                "вас к новому достижению и увлекательным приключениям в реальной жизни.";
    }

    public static String createTextForTaskScreen(String taskFromUser) {
        return String.format(""" 
                Активные задания:
                %s
             
                Последние 3 выполненых задания:
                %s
                """,
                taskFromUser,
                "Пока не реализовано"
        );
    }

}
