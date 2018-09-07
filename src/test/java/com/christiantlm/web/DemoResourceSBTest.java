package com.christiantlm.web;

import com.alibaba.fastjson.JSON;
import com.christiantlm.model.User;
import com.christiantlm.service.DemoService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by dot.zhou on 2018/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoResourceSBTest {

    List<User> expectedOriginal = new ArrayList<>(
            Arrays.asList(
                    new User("1", "aaa"),
                    new User("2", "bbb"),
                    new User("3", "ccc")));

    @Autowired
    private MockMvc mvc;

    @Mock
    private DemoService demoService;

    @Test
    public void hello() throws Exception {

        String expectResult = "Hello world!";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectResult));
    }

    @Test
    public void getUsers() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON.toJSONString(expectedOriginal)));
    }

    @Test
    public void getUsersById() throws Exception {

        Integer id = RandomUtils.nextInt(0, 3);
        String expected = JSON.toJSONString(expectedOriginal.stream()
                .filter(u -> String.valueOf(id).equals(u.getUserid()))
                .collect(Collectors.toList()));

        when(demoService.getUsers(id.toString()))
                .thenReturn(Collections.singletonList(new User("222", "aabb")));

        mvc.perform(get("/users/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

        System.out.println("<<<<<<<id:" + id + ",result:" + expected);
    }

}