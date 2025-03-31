package com.pragma.mealssquare.domain.api;

import com.pragma.users.domain.model.User;
import java.util.List;

public interface IUserServicePort {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(String email);

    void updateUser(User user);

    void deleteUser(String email);
}
