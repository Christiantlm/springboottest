package com.christiantlm.web;

import com.alibaba.fastjson.JSON;
import com.christiantlm.model.User;
import com.christiantlm.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
//@RunWith(SpringRunner.class)
//@ContextConfiguration
//@WebAppConfiguration(value = "src/main/java/com/christiantlm/web")
public class DemoResourceTest {

    List<User> expectedOriginal = new ArrayList<>(
            Arrays.asList(
                    new User("1", "aaa"),
                    new User("2", "bbb"),
                    new User("3", "ccc")));

    private MockMvc mvc;


    @InjectMocks
    private DemoResource demoResource;

    @Mock
    private DemoService demoService;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mvc = MockMvcBuilders.standaloneSetup(demoResource).build();
    }

    @Test
    public void hello() throws Exception {

        String expectResult = "Hello world!";

        when(demoService.hello()).thenReturn(expectResult);

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

        Integer id = 2;//RandomUtils.nextInt(0, 3);
        String expected = JSON.toJSONString(expectedOriginal.stream()
                .filter(u -> String.valueOf(id).equals(u.getUserid()))
                .collect(Collectors.toList()));

        when(demoService.getUsers(id.toString()))
                .thenReturn(Collections.singletonList(new User("2", "bbb")));

        mvc.perform(get("/users/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
        mvc.perform(get("/users/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

        MockingDetails md = Mockito.mockingDetails(demoService);
        System.out.println("mock times:" + md.getInvocations().size());
        System.out.println("<<<<<<<id:" + id + ",result:" + expected);
    }

}