package hello.controller;


import hello.Application;
import hello.entity.User;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

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

    @Autowired
    private UserController userController;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/user/list"))
                .andExpect(status().isOk()).andExpect(content().string(containsString("{\"2048\":{\"password\":\"qwe\",\"name\":\"Oli\"},\"9527\":{\"password\":\"asd\",\"name\":\"Leo\"}}")));
    }

    @Test
    public void getUserByAnExistingId() throws Exception {
        int id = 9527;
        mockMvc.perform((MockMvcRequestBuilders.get("http://localhost:8080/user/{id}",id)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andExpect(content().string(containsString("{\"password\":\"asd\",\"name\":\"Leo\"}")));

    }

    @Test
    public void getUserByAnNotExistingId() throws Exception {
        int id = 1024;
        mockMvc.perform((MockMvcRequestBuilders.get("http://localhost:8080/user/{id}",id)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print()).andExpect(content().string(containsString("")));

    }

    @Test
    public void addUser() throws Exception {
        int id = 3096;
        User user = new User("you","Lorence");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/user/{id}?password={password}&name={name}",id,user.getPassword(),user.getName()))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteByAnExistingId() throws Exception {
        int id = 9527;
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/user/{id}",id))
                .andExpect(status().isNoContent()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTheExistingUser() throws Exception {
        Integer id = 9527;
        String name = "Helo";
        String password = "test";
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/user/{id}?name={name}&password={password}",id,name,password))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTheNotExistingUser() throws Exception {
        Integer id = 15;
        String name = "Frank";
        String password = "test2";
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/user/{id}?name={name}&password={password}",id,name,password))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }


}