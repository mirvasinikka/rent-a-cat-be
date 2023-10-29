package Harjoitustyo.RentACatBE.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Harjoitustyo.RentACatBE.domain.Address;
import Harjoitustyo.RentACatBE.domain.AddressRepository;
import Harjoitustyo.RentACatBE.domain.RegisterForm;
import Harjoitustyo.RentACatBE.domain.User;
import Harjoitustyo.RentACatBE.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private AddressRepository arepository;
	
	@Autowired
	private UserRepository urepository;
	
	@GetMapping("/users")
	public String userList(Model model) {
		model.addAttribute("users", urepository.findAll());
		return "users";
	}
	
	@GetMapping("/register")
	public String addUser(Model model) {
		model.addAttribute("registerform", new RegisterForm());
		return "register";
	}
	
	@PostMapping("/saveuser")
    public String save(@Valid @ModelAttribute("registerform") RegisterForm registerForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { 
    		if (registerForm.getPassword().equals(registerForm.getPasswordCheck())) {	
	    		String pwd = registerForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
		    	

		    	User newUser = new User();
		    	Address userAddress = new Address(registerForm.getStreet(), registerForm.getCity(), registerForm.getPostCode());
		    	
		    	if (userAddress.getAddress_id() == null) {
					arepository.save(userAddress);
		    
		    	}
		    
		    	newUser.setPassword(hashPwd);
		    	newUser.setUsername(registerForm.getUsername());
		    	newUser.setEmail(registerForm.getEmail());
				newUser.setfName(registerForm.getFirstName());
				newUser.setlName(registerForm.getLastName());
				newUser.setAddress(userAddress);
				
	
					
				
		    	newUser.setRole("USER");
		    	
		    	if (urepository.findByUsername(registerForm.getUsername()) == null) { 
		    		urepository.save(newUser);
		    		
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists // Käyttäjätunnus on jo olemassa");    	
	    			return "register";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match // salasana ei täsmää");    	
    			return "register";
    		}
    	}
    	else {
    		return "register";
    	}  
		return "redirect:/list";    	
    }
	
	@GetMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		
		 User userToDelete = urepository.findById(id).orElse(null);
		    
		    if (userToDelete != null) {
		        urepository.delete(userToDelete);
		    }
		return "redirect:/users";
	}
}
