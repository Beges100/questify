package ru.writebot.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.writebot.myapp.entity.Task;
import ru.writebot.myapp.repository.TaskRepository;

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
}
