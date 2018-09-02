package com.christiantlm.service;

import com.christiantlm.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dot.zhou on 2018/9/1.
 */
public interface DemoService {

    List<User> globalUsers = new ArrayList<>(
            Arrays.asList(
                    new User("1", "aaa"),
                    new User("2", "bbb"),
                    new User("3", "ccc")));

    String hello();

    List<User> getUsers(String userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);
}
