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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Harjoitustyo.RentACatBE.domain.Address;
import Harjoitustyo.RentACatBE.domain.AddressRepository;
import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;
import Harjoitustyo.RentACatBE.domain.AppUser;
import Harjoitustyo.RentACatBE.domain.AppUserRepository;
import jakarta.validation.Valid;

import java.util.Base64;

@Controller
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AppUserRepository userRepository;
    
 

    @GetMapping("/main")
    @ResponseBody
    public String showMainPage() {
        return "Tämä on pääsivu :) jee";
    }

    @GetMapping("/")
    public String catList(Model model) {
        try {   
            Iterable<Cat> cats = catRepository.findAll();

            for(Cat cat : cats) {
                byte[] image = cat.getImage();

                if (image != null) {
                    String base64Image = Base64.getEncoder().encodeToString(image);
                    cat.setBase64Image(base64Image);
                }
            }

            model.addAttribute("cats", cats);
            return "catList";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }

    @GetMapping("/addCat")
    public String addCat(Model model) {
        model.addAttribute("cat", new Cat());
        return "addCat";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult, Model model, Principal principal, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (bindingResult.hasErrors()) {
                return "add";
            }

            Address address = cat.getAddress();

            if (address != null && address.getAddress_id() == null) {
                address = addressRepository.save(address);
            }

            cat.setAddress(address);

            if (principal != null) {
                String username = principal.getName();
                AppUser user = userRepository.findByUsername(username);

                cat.setUser(user);
            }



            if (!imageFile.isEmpty()) {
                byte[] imageBytes = imageFile.getBytes();
                cat.setImage(imageBytes);
            }

            catRepository.save(cat);
            return "redirect:/";
        } catch (Exception e) {
      
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCat(@PathVariable("id") Long id, Model model) {
        try {
            catRepository.deleteById(id);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }

    @GetMapping("/editCat/{id}")
    public String editCat(@PathVariable("id") Long id, Model model) {
        try {
            Cat cat = catRepository.findById(id).orElse(new Cat());

            
            byte[] image = cat.getImage();
            if (image != null) {
                String base64Image = Base64.getEncoder().encodeToString(image);
                cat.setBase64Image(base64Image);
            }

            model.addAttribute("cat", cat);
            model.addAttribute("address", cat.getAddress());
            return "editCat";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }


    @PostMapping("/edit/{id}")
    public String saveEditedCat(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult, Model model, Principal principal) {
           
      
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
                AppUser user = userRepository.findByUsername(username);
                cat.setUser(user);   
            }

            if (cat.getImage() == null) {
                Cat oldCat = catRepository.findById(cat.getId()).orElse(new Cat());
                cat.setImage(oldCat.getImage());
            }

            catRepository.save(cat);
            return "redirect:/";
        } catch (Exception e) {
      
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }
    }
}

