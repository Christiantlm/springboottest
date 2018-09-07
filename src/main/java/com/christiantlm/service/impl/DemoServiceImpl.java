package com.christiantlm.service.impl;

import com.christiantlm.model.User;
import com.christiantlm.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dot.zhou on 2018/9/1.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String hello() {
        return "Hello world!";
    }

    @Override
    public List<User> getUsers(String userId) {
        return globalUsers.stream()
                .filter(user -> userId.equals(user.getUserid()))
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(User user) {
        globalUsers.add(user);
    }

    @Override
    public void updateUser(User user) {

        List<User> users = globalUsers.stream()
                .filter(u -> user.getUserid().equals(u.getUserid()))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(users)) {
            for (User updateUser : users) {
                globalUsers.remove(updateUser);
                globalUsers.add(user);
            }
        }

    }

    @Override
    public void deleteUser(String userId) {
        globalUsers.removeAll(getUsers(userId));
    }

}
