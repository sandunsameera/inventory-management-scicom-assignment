package com.sandun.controller;

import com.sandun.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());

    }

    @Test
    public void login() throws Exception{
        String json =   "{\n" +
                "  \"email\": \"sandunsameera25@gmail.com\",\n" +
                "  \"password\": \"sandun\"\n" +
                "}";

        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void register() throws Exception{
        String json =  "{\n" +
                "  \"email\": \"sandunsameera25@gmail.com\",\n" +
                "  \"password\": \"sandun\",\n" +
                "  \"id\": \"10\"\n" +
                "}";
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk());
    }
}
