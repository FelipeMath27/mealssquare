package com.pragma.mealssquare.domain.usecase;


import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IUserPersistencePort;

import java.util.List;

public class UseCaseUser implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UseCaseUser(IUserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user){
        userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUser();
    }

    @Override
    public User getUser(String email) {
        return userPersistencePort.getUser(email);
    }

}
