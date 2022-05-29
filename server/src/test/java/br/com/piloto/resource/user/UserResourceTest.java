package br.com.piloto.resource.user;

import br.com.piloto.domain.user.User;
import br.com.piloto.service.user.UserService;
import br.com.piloto.test.ResourceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ResourceTest
public class UserResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAll() throws Exception {

        User user1 = new User();
        user1.setId(UUID.randomUUID());

        User user2 = new User();
        user2.setId(UUID.randomUUID());

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(this.service.findAll()).thenReturn(userList);

        this.mockMvc.perform(get("/api/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id",
                        hasItems(user1.getId().toString(), user2.getId().toString())));

        verify(this.service).findAll();
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void findById() throws Exception {

        User user = new User();
        user.setId(UUID.randomUUID());

        when(this.service.findById(user.getId())).thenReturn(user);

        this.mockMvc.perform(get("/api/users/{id}", user.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(user.getId().toString())));

        verify(this.service).findById(user.getId());
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void save() throws Exception {

        User user = new User();
        user.setId(UUID.randomUUID());

        doNothing().when(this.service).saveUser(user);

        this.mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        verify(this.service).saveUser(user);
        verifyNoMoreInteractions(this.service);
    }


    @Test
    public void update() throws Exception {

        User user = new User();
        user.setId(UUID.randomUUID());

        doNothing().when(this.service).updateUser(user);

        this.mockMvc.perform(put("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        verify(this.service).updateUser(user);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void deleteUser() throws Exception {

        User user = new User();
        user.setId(UUID.randomUUID());

        when(this.service.findById(user.getId())).thenReturn(user);
        doNothing().when(this.service).deleteUser(user);

        this.mockMvc.perform(delete("/api/users/{id}", user.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());

        verify(this.service).findById(user.getId());
        verify(this.service).deleteUser(user);
        verifyNoMoreInteractions(this.service);
    }
}