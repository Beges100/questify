package ru.writebot.myapp.service.screenservice;

import org.springframework.stereotype.Service;
import ru.writebot.myapp.entity.VerificationTask;
import ru.writebot.myapp.repository.TaskRepository;
import ru.writebot.myapp.repository.VerificationTaskRepository;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.service.ServiceButton;

import java.util.List;
import java.util.Map;

@Service
public class RandomVerificationTasksScreenService {
    private final ServiceButton serviceButton;
    private final VerificationTaskRepository verificationTaskRepository;
    private final TaskRepository taskRepository;

    public RandomVerificationTasksScreenService(ServiceButton serviceButton,
                                                VerificationTaskRepository verificationTaskRepository,
                                                TaskRepository taskRepository) {
        this.serviceButton = serviceButton;
        this.verificationTaskRepository = verificationTaskRepository;
        this.taskRepository = taskRepository;
    }

    public Screen getRandomVerificationTasksScreen() {
        List<VerificationTask> verificationTasks = verificationTaskRepository.findRandomVerificationTasks();

        StringBuilder sb = new StringBuilder();
        verificationTasks.forEach(verificationTask -> {
            sb.append(taskRepository.findById(verificationTask.getTaskId()).get().toStringNameForOneTask()).append("\n");
        });

        String tasksOnVerification = verificationTasks.isEmpty() ? "Нет заданий на проверке" : sb.toString();

        return Screen.builder()
                .textOnScreen("Случайные задания на проверке:\n" + tasksOnVerification)
                .keyboard(
                        serviceButton.createKeyboard(
                                Map.of(
                                        1, List.of("🔍Главная", "👤Профиль"),
                                        2, List.of("Мои друзья", "Найти друга"),
                                        3, List.of("К заданиям")
                                )
                        )
                )
                .build();
    }
}
