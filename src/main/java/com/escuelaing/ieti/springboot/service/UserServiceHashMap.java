package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceHashMap implements UserService{
    private HashMap<Integer, User> users = new HashMap<Integer, User>();

    @Override
    public User create(User user) {
        if (users.containsKey(user.getId())) {
            return null;
        } else {
            users.put(user.getId(), user);
            return users.get(user.getId());
        }
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
        if (users.containsKey(Integer.parseInt(userId))) {
            users.replace(Integer.parseInt(userId), user);
        }
        return users.get(Integer.parseInt(userId));
    }
}
