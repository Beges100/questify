package ru.writebot.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.writebot.myapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
