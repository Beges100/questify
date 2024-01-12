package ru.writebot.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TaskServices {

    private final TaskRepository taskRepository;

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    public List<Task> getThreeRandomTasks() {
        long count = taskRepository.count();
        List<Task> allTasks = taskRepository.findAll();

        List<Task> threeRandomTasks = new ArrayList<>();

        if (count > 0) {
            Random random = new Random();

            // Получаем три случайные задачи
            while(threeRandomTasks.size() < 3) {
                int randomIndex = random.nextInt((int) count);
                Task task = allTasks.get(randomIndex);
                if (!threeRandomTasks.contains(task)) {
                    threeRandomTasks.add(allTasks.get(randomIndex));
                }
            }
        }

        return threeRandomTasks;
    }
}
