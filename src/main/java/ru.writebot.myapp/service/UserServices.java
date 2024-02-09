package ru.writebot.myapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.writebot.myapp.entity.User;
import ru.writebot.myapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
