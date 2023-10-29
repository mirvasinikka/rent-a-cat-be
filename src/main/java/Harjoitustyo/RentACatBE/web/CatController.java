package Harjoitustyo.RentACatBE.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Harjoitustyo.RentACatBE.domain.Address;
import Harjoitustyo.RentACatBE.domain.AddressRepository;
import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;
import Harjoitustyo.RentACatBE.domain.User;
import Harjoitustyo.RentACatBE.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	

    @RequestMapping("/main")
    @ResponseBody
    public String showMainPage() {
        return "Tämä on pääsivu :) jee";
    }

    @GetMapping("/list")
    public String catList(Model model) {
        try {
            model.addAttribute("cats", catRepository.findAll());
            return "list";
        } catch (Exception e) {
          
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }

    @GetMapping("/add")
    public String addCat(Model model) {
        model.addAttribute("cat", new Cat());
        return "add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult, Model model, Principal principal) {
        try {
            if (bindingResult.hasErrors()) {
                return "edit";
            }

            Address address = cat.getAddress();

            if (address != null && address.getAddress_id() == null) {
                address = addressRepository.save(address);
            }

            cat.setAddress(address);

            if (principal != null) {
                String username = principal.getName();
                User user = userRepository.findByUsername(username);

                cat.setUser(user);
                catRepository.save(cat);
            }

            catRepository.save(cat);
            return "redirect:/list";
        } catch (Exception e) {
      
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCat(@PathVariable("id") Long id) {
        try {
            catRepository.deleteById(id);
            return "redirect:/list";
        } catch (Exception e) {
        
            return "errorpage";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCat(@PathVariable("id") Long id, Model model) {
        try {
            Cat cat = catRepository.findById(id).orElse(new Cat());
            model.addAttribute("cat", cat);
            model.addAttribute("address", cat.getAddress());
            return "edit";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }
}
