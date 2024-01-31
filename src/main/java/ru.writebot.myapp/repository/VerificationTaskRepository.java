package ru.writebot.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.writebot.myapp.entity.VerificationTask;

import java.util.List;

@Repository
public interface VerificationTaskRepository extends JpaRepository<VerificationTask, Long> {
    @Query(value = "SELECT * FROM verification_task ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<VerificationTask> findRandomVerificationTasks();
}
