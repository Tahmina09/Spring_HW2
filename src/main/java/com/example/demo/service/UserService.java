package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findUsers() {
        return repository.findAll();
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    public void updateUser(User user) {
        repository.updateUser(user);
    }

    public User getUserById(Integer id) {
        return repository.getOne(id);
    }
}
