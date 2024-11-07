package org.example.services;

import org.example.repositories.UserRepository;
import org.example.models.User;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public String register(String username, String password, String role) {
        if (!isPasswordUnique(password)) {
            return "Пароль должен быть уникальным";
        }

        if (role.equals(User.ROLE_ADMIN) && hasAdmin()) {

            // TODO: 29.10.2024  добавить логику добавления заявки в сервис ApprovalRequestService
            return "Требуется одобрение существующего администратора";
        }

        User user = new User(username, password, role);
        userRepository.save(user);
        return "Пользователь успешно зарегистрирован";
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }


    private boolean isPasswordUnique(String password) {
        return userRepository.findAll().stream()
                .noneMatch(user -> user.getPassword().equals(password));
    }

    private boolean hasAdmin() {
        return userRepository.findAll().stream()
                .anyMatch(user -> User.ROLE_ADMIN.equals(user.getRole()));
    }

}
