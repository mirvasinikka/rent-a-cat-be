package Harjoitustyo.RentACatBE;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import Harjoitustyo.RentACatBE.domain.AppUser;
import Harjoitustyo.RentACatBE.domain.AppUserRepository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;




@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppUserRepositoryTest {
	
	 @Autowired
	    private WebApplicationContext webAppContext;

	    private MockMvc mockMvc;
	    
	    @Autowired
	    private AppUserRepository userRepository; 

	    @BeforeEach
	    public void setUp() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	    }

	@Test
	void findByUsername() {
		AppUser user = userRepository.findByUsername("user");
		assertThat(user).isNotNull();
	}

	    @Test
	    public void saveUser() throws Exception {
	    	AppUser appUser = new AppUser();
	    	
	    	appUser.setUsername("test");
	    	appUser.setPassword("test");
	    	appUser.setRole("USER");
	    	appUser.setfName("Molla");
	        appUser.setlName("Maija");
	        appUser.setEmail("molla.maija@mi.fi");

	        AppUser saveuser = userRepository.save(appUser);
	        assertNotNull(saveuser);
	        
	        assertThat(saveuser.getId()).isNotNull();
	        assertThat(saveuser.getUsername()).isEqualTo("test");
	        assertThat(saveuser.getPassword()).isEqualTo("test");
	        assertThat(saveuser.getRole()).isEqualTo("USER");
	        assertThat(saveuser.getfName()).isEqualTo("Molla");
			assertThat(saveuser.getlName()).isEqualTo("Maija");
			assertThat(saveuser.getEmail()).isEqualTo("molla.maija@mi.fi");
	        
	     }

	@Test
    public void deleteUser() throws Exception {
        AppUser userToDelete = new AppUser();
        userToDelete.setUsername("testuser");
        userToDelete = userRepository.save(userToDelete);
        mockMvc.perform(MockMvcRequestBuilders.get("/deleteuser/{id}", userToDelete.getId()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) 
                .andExpect(MockMvcResultMatchers.view().name("redirect:/users")); 
    }

		 
}
