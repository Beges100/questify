package ru.writebot.myapp.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.writebot.myapp.entity.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"currentTasks", "completedTasks", "achievements" , "personalQualities"})
    @Override
    Optional<User> findById(Long aLong);
}
