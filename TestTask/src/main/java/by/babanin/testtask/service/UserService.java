package by.babanin.testtask.service;

import by.babanin.testtask.entity.User;

public interface UserService {
    User getUserByUsername(String username);

    User addUser(User user);
}
