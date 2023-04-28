package com.akash.spring_junit_test_app.user;

import com.akash.spring_junit_test_app.user.enities.User;
import com.akash.spring_junit_test_app.user.services.UserServices;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserServices userServices;

    TestRestTemplate restTemplate = new TestRestTemplate();

    User dummyUser = new User(27, "lores", "epsum", "loresepsum@gmail.com");
    String expectedJSON = "{\"id\": 27, \"firstName\": \"lores\", \"lastName\": \"epsum\", \"email\": \"loresepsum@gmail.com\"}";

    String expectedJSONAll = "[ { \"id\": 1, \"firstName\": \"Mahavir\", \"lastName\": \"Ojha\", \"email\": \"m@gmail.com\" }, { \"id\": 2, \"firstName\": null, \"lastName\": \"Ram\", \"email\": \"akash@gmail.com\" }, { \"id\": 3, \"firstName\": \"Rakesh\", \"lastName\": \"Kumar\", \"email\": \"mj@gmail.com\" }, { \"id\": 5, \"firstName\": null, \"lastName\": \"Tiwary\", \"email\": \"rakesh@gmail.com\" }, { \"id\": 6, \"firstName\": null, \"lastName\": \"Tiwary\", \"email\": \"rakesh@gmail.com\" }, { \"id\": 7, \"firstName\": null, \"lastName\": \"Tiwary\", \"email\": \"rakesh@gmail.com\" }, { \"id\": 8, \"firstName\": null, \"lastName\": \"Tiwarytt\", \"email\": \"rakesggh@gmail.com\" }, { \"id\": 9, \"firstName\": \"Rakesh\", \"lastName\": \"Tiwarytt\", \"email\": \"rakesggh@gmail.com\" } ]";

    List<User> dummyUserList = List.of(
            new User(1, "Mahavir", "ojha", "m@gmail.com"),
            new User(2, "Mahavir1", "ojha1", "m1@gmail.com"),
            new User(3, "Mahavir2", "ojha2", "m2@gmail.com"),
            new User(4, "Mahavir3", "ojha3", "m3@gmail.com"),
            new User(5, "Mahavir4", "ojha4", "m4@gmail.com"),
            new User(6, "Mahavir5", "ojha5", "m5@gmail.com"),
            new User(7, "Mahavir6", "ojha6", "m6@gmail.com"),
            new User(8, "Mahavir7", "ojha7", "m7@gmail.com"));

    @Test
    @Order(3)
    @DisplayName("Test Get All Users Module | non null, content type and size")
    public void testGetAllUsersEndpoint() throws Exception {
        System.out.println(dummyUserList);
        when(userServices.getUsers()).thenReturn(dummyUserList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0].*", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is(notNullValue())))
                .andExpect(jsonPath("$[0].*", hasItem("Mahavir")));
    }

    @Test
    @Order(2)
    @DisplayName("Test Get User By Id Module | non null, content type and content")
    public void testGetUserByIdEndpoint() throws Exception {
        when(userServices.getUserById(Mockito.anyInt())).thenReturn(dummyUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/{id}", 27);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(notNullValue()))).andReturn();
        System.out.println(mvcResult.getResponse());
        JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    @Order(1)
    @DisplayName("Test Add User Module | non null, content type and content")
    public void testAddUserEndpoint() throws Exception {
        when(userServices.addUser(Mockito.any(User.class))).thenReturn(dummyUser);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/")
                        .accept(MediaType.APPLICATION_JSON).content(expectedJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(notNullValue()))).andReturn();
        System.out.println(mvcResult.getResponse());
        JSONAssert.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(), false);
    }
}
