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


import Harjoitustyo.RentACatBE.domain.RegisterForm;
import Harjoitustyo.RentACatBE.domain.User;
import Harjoitustyo.RentACatBE.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String userList(Model model) {
        try {
            model.addAttribute("users", userRepository.findAll());
            return "users";
        } catch (Exception e) {
            
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }

    @GetMapping("/register")
    public String addUser(Model model) {
        model.addAttribute("registerform", new RegisterForm());
        return "register";
    }

    @PostMapping("/saveuser")
    public String save(
            @Valid @ModelAttribute("registerform") RegisterForm registerForm,
            BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                if (registerForm.getPassword().equals(registerForm.getPasswordCheck())) {
                    String pwd = registerForm.getPassword();
                    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                    String hashPwd = bc.encode(pwd);

                    User newUser = new User(
                            registerForm.getUsername(),
                            registerForm.getEmail(),
                            hashPwd,
                            registerForm.getRole(),
                            registerForm.getFirstName(),
                            registerForm.getLastName()
                    );

                    newUser.setAddress(registerForm.getAddress());

                    if (userRepository.findByUsername(registerForm.getUsername()) == null) {
                        userRepository.save(newUser);
                    } else {
                        bindingResult.rejectValue("username", "err.username", "Username already exists // Käyttäjätunnus on jo olemassa");
                        return "register";
                    }
                } else {
                    bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match // Salasanat eivät täsmää");
                    return "register";
                }
            } else {
                return "register";
            }
            return "redirect:/users";
        } catch (Exception e) {
            
            return "errorpage";
        }
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        try {
            User userToDelete = userRepository.findById(id).orElse(null);
            if (userToDelete != null) {
                userRepository.delete(userToDelete);
            }
            return "redirect:/users";
        } catch (Exception e) {
          
            return "errorpage";
        }
    }
}
