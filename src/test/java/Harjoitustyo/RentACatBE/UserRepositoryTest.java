package Harjoitustyo.RentACatBE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import Harjoitustyo.RentACatBE.domain.User;
import Harjoitustyo.RentACatBE.domain.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class UserRepositoryTest {

	
	 @Autowired
	    private WebApplicationContext webAppContext;

	    private MockMvc mockMvc;
	    
	    @Autowired
	    private UserRepository userRepository; 

	    @BeforeEach
	    public void setUp() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	    }

	    @Test
	    public void userList() throws Exception {
	        mockMvc.perform(get("/users"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("users"));
	    }

	    @Test
	    public void addUserForm() throws Exception {
	        mockMvc.perform(get("/register"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("register"))
	                .andExpect(model().attributeExists("registerform"));
	    }

	    @Test
	    public void saveUserValid() throws Exception {
	        String username = "testuser";
	        String password = "testpassword";
	        String role = "USER";
	        String fName = "John";
	        String lName = "Doe";
	        String email = "john.doe@example.com";

	        mockMvc.perform(MockMvcRequestBuilders.post("/saveuser")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("username", username)
	                .param("password", password)
	                .param("role", role)
	                .param("fName", fName)
	                .param("lName", lName)
	                .param("email", email)
	                .param("street", "123 Main St")
	                .param("city", "Example City")
	                .param("postCode", "12345"))
	        		.andExpect(MockMvcResultMatchers.redirectedUrl("/list"));

	        User savedUser = userRepository.findByUsername(username);
	        assertNotNull(savedUser);
	        assertEquals(username, savedUser.getUsername());
	    }
}
