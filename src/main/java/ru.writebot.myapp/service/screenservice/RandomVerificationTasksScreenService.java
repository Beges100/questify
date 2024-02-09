package ru.writebot.myapp.service.screenservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.entity.VerificationTask;
import ru.writebot.myapp.repository.TaskRepository;
import ru.writebot.myapp.repository.VerificationTaskRepository;
import ru.writebot.myapp.screens.Screen;
import ru.writebot.myapp.utils.ScreenButtonsType;
import ru.writebot.myapp.utils.ScreenUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RandomVerificationTasksScreenService {
    private final VerificationTaskRepository verificationTaskRepository;
    private final TaskRepository taskRepository;
    private final ScreenUtils screenUtils;

    public Screen getRandomVerificationTasksScreen() {
        List<VerificationTask> verificationTasks = verificationTaskRepository.findRandomVerificationTasks();

        String tasksOnVerification = verificationTasks.stream()
                .map(verificationTask -> taskRepository.findById(verificationTask.getTaskId())
                        .map(Task::toStringNameForOneTask)
                        .orElse(""))
                .collect(Collectors.joining("\n"));

        String screenText = "Случайные задания на проверке:\n" + (tasksOnVerification.isEmpty() ? "Нет заданий на проверке" : tasksOnVerification);

        return screenUtils.createScreenWithButtons(
                screenText,
                ScreenButtonsType.RANDOM_VERIFICATION_SCREEN.getTypeScreenButtons()
        );
    }
}
