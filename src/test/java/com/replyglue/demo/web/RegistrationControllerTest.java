package com.replyglue.demo.web;

import com.google.gson.Gson;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.service.RegistrationService;
import com.replyglue.demo.util.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.GregorianCalendar;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private TestRestTemplate restTemplate;

    @MockBean
    private RegistrationService registrationService;

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(
                "r1Chard", "passWord123",
                "rich@me.com", new GregorianCalendar(1984, 5, 9)
        );
    }

    @Test
    public void test_getAllUsers_shouldReturn_listOfUsers() throws Exception {
        //TODO - clean this code
//        given(registrationService.getAllUers())
//                .willReturn(Arrays.asList(new User[]{
//                      new User("r1Chard", "passWord123", "rich@me.com", new GregorianCalendar(1984, 5, 9)),
//                      new User("p1Eman", "pWord123", "richrich@me.com", new GregorianCalendar(1972, 12, 28))
//                 }));

        given(registrationService.findUsersByUsername(anyString()))
                .willReturn(new User(
                        "r1Chard",
                        "passWord123",
                        "rich@me.com",
                        new GregorianCalendar(1984, 5, 9))
                );

        mockMvc.perform(MockMvcRequestBuilders.get("/users/r1Chard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("r1Chard"))
                .andExpect(jsonPath("password").value("passWord123"));
    }

    @Test
    public void getUser_notFound() throws Exception {
        given(registrationService.findUsersByUsername(anyString())).willThrow(new UserNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/meme1Dme"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUserResponseBody_shouldReturn_statusOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                        .content(new Gson().toJson(testUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
