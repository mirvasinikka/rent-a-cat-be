package Harjoitustyo.RentACatBE;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import Harjoitustyo.RentACatBE.domain.AppUser;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppUserRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    @Test
	public void everytIsOk() throws Exception {
		mockMvc.perform(get("/userlist")).andExpect(status().isOk());
	}

    @Test
	public void responseTypeJson() throws Exception {
		mockMvc.perform(get("/userlist"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

    @Test
    public void AddUser() throws Exception {
        AppUser newUser = new AppUser();
        newUser.setUsername("test");

        ObjectMapper objectMapper = new ObjectMapper();
        String catJson = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post("/userlist")
            .contentType(MediaType.APPLICATION_JSON)
            .content(catJson))
            .andExpect(status().isCreated());
    }

    @Test
    public void deleteUserNotFound() throws Exception {
        mockMvc.perform(delete("/userlist/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}
