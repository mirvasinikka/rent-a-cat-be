package Harjoitustyo.RentACatBE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import Harjoitustyo.RentACatBE.domain.Cat;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CatRestTest {
	
	@Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

	@Test
	public void everytIsOk() throws Exception {
		mockMvc.perform(get("/cats")).andExpect(status().isOk());
	}

	@Test
	public void responseTypeJson() throws Exception {
		mockMvc.perform(get("/cats"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


    @Test
    public void restAddCat() throws Exception {
        Cat newCat = new Cat();
        newCat.setName("test");
        newCat.setBreed("test");
        newCat.setToy("test");

        ObjectMapper objectMapper = new ObjectMapper();
        String catJson = objectMapper.writeValueAsString(newCat);

        mockMvc.perform(post("/cat")
            .contentType(MediaType.APPLICATION_JSON)
            .content(catJson))
            .andExpect(status().isCreated());
    }


    @Test
    public void deleteCat() throws Exception {
        mockMvc.perform(delete("/cat/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCatNotFound() throws Exception {
        mockMvc.perform(delete("/cat/{id}", 999L))
                .andExpect(status().isNotFound());
    }

}
