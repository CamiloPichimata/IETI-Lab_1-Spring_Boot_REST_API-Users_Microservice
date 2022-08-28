package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceHashMap implements UserService{
    private Integer id = 0;
    private HashMap<Integer, User> users;

    @Override
    public User create(User user) {
        users.put(id, user);
        id++;
        return user;
    }

    @Override
    public User findById(String id) {
        return users.get(Integer.parseInt(id));
    }

    @Override
    public List<User> getAll() {
        Collection<User> values = users.values();
        return new ArrayList<User>(values);
    }

    @Override
    public void deleteById(String id) {
        users.remove(Integer.parseInt(id));
    }

    @Override
    public User update(User user, String userId) {
        users.put(Integer.parseInt(userId), user);
        return user;
    }
}
