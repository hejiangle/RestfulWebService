package com.tw.jlhe.practice.controller;


import com.tw.jlhe.practice.Application;
import com.tw.jlhe.practice.entity.User;


import com.tw.jlhe.practice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by cqu on 27/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Spy
    @Autowired
    private UserService userServiceMock;

    @InjectMocks
    @Autowired
    private UserController userController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getListMethodShouldReturnUserListWithJSON() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/users"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andExpect(content().string(containsString("[{\"id\":9527,\"password\":\"asd\",\"name\":\"Leo\"}]")));
    }

    @Test
    public void getUserByIdMethodShouldReturnIsOkStatusAndAnUserWithJSONWhenInputAnExcitingId() throws Exception {
        int id = 9527;
        mockMvc.perform((MockMvcRequestBuilders.get("http://localhost:8080/users/{id}",id)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andExpect(content().string(containsString("{\"id\":9527,\"password\":\"asd\",\"name\":\"Leo\"}")));

    }

    @Test
    public void getUserByIdMethodShouldReturnNotFoundStatusAndNullStringWhenInputAnNotExistingId() throws Exception {
        int id = 1024;
        mockMvc.perform((MockMvcRequestBuilders.get("http://localhost:8080/users/{id}",id)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print()).andExpect(content().string(containsString("")));

    }

    @Test
    public void addUserMethodShouldReturnIsCreatedStatus() throws Exception {
        User user = new User(3096L,"you","Lorence");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/users/{id}?password={password}&name={name}",user.getId(),user.getPassword(),user.getName()))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteByIdMethodShouldReturnIsNoContentStatusWhenInputAnExistingId() throws Exception {
        Long id = 9527L;
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/users/{id}",id))
                .andExpect(status().isNoContent()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteByIdMethodShouldReturnIsNoContentStatusWhenInputAnNotExistingId() throws Exception {
        Long id = 567L;
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/users/{id}",id))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserMethodShouldReturnIsForbiddenStatusWhenInputAnExistingIdAndExistingUserInfo() throws Exception {
        Long id = 9527L;
        String name = "Leo";
        String password = "asd";
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/users/{id}",id,name,password))
                .andExpect(status().isForbidden()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserMethodShouldReturnIsOkStatusWhenInputAnExistingIdAndAnExistingUserNameWithAnyPassword() throws Exception {
        Long id = 9527L;
        String name = "Leo";
        String password = "test";
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/users/{id}?name={name}&password={password}",id,name,password))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserMethodShouldReturnIsOkStatusWhenInputAnExistingIdAndAnNotExistingUserNameWithAnyPassword() throws Exception {
        Long id = 9527L;
        String name = "Frank";
        String password = "test2";
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/users/{id}?name={name}&password={password}",id,name,password))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserMethodShouldReturnIsCreatedStatusWhenInputAnNotExistingId() throws Exception{
        Long id = 89757L;
        String name = "JJ";
        String password = "munaiyi";
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/users/{id}?name={name}&password={password}",id,name,password))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }


}