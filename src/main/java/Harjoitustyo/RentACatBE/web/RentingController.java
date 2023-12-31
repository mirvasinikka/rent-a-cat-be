package Harjoitustyo.RentACatBE.web;

import java.security.Principal;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;
import Harjoitustyo.RentACatBE.domain.Renting;
import Harjoitustyo.RentACatBE.domain.RentingForm;
import Harjoitustyo.RentACatBE.domain.RentingRepository;
import Harjoitustyo.RentACatBE.domain.AppUser;
import Harjoitustyo.RentACatBE.domain.AppUserRepository;
import jakarta.validation.Valid;

@Controller
public class RentingController {

	@Autowired
	private CatRepository catRepository; 

	@Autowired
	private RentingRepository rentingRepository;

    @Autowired
    private AppUserRepository userRepository;
	

	@GetMapping("/rent")
	public String showRentingForm(@RequestParam("catId") Long catId, Model model) {
		Cat selectedCat = catRepository.findById(catId).orElse(null);
		
	try {
		
        if (selectedCat != null) {
            model.addAttribute("selectedCat", selectedCat);

            RentingForm rentingForm = new RentingForm();
            model.addAttribute("rentingForm", rentingForm);

            return "rent";
        } else {
            model.addAttribute("errorMessage", "Cat not found");
            return "errorpage";
            
        }

        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "errorpage";
        }

    }

   

	@PostMapping("/rent")
	public String processRentingForm(@Valid @ModelAttribute("rentingForm") RentingForm rentingForm, BindingResult bindingResult, Model model, Principal principal) {


    if (bindingResult.hasErrors()) {
        Cat selectedCat = catRepository.findById(rentingForm.getCatId()).orElse(null);
        model.addAttribute("selectedCat", selectedCat);
        return "rent";
    }

    Long catId = rentingForm.getCatId();
    Date rentalDate = new Date();
    int rentalDuration = rentingForm.getRentalDuration();

    Cat cat = catRepository.findById(catId).orElse(null);
    

    try {
        if (cat != null && principal != null) {
            String username = principal.getName();
            AppUser user = userRepository.findByUsername(username);

            cat.setUser(user);
            catRepository.save(cat);
    
            Renting renting = new Renting();
            renting.setCat(cat);
            renting.setRentalDate(rentalDate);
            renting.setRentalDuration(rentalDuration);
            renting.setUser(user);

            rentingRepository.save(renting);

            catRepository.save(cat);

            model.addAttribute("successMessage", "Cat rented successfully!");

            model.addAttribute("cat", cat);
            model.addAttribute("user", user);
            model.addAttribute("renting", renting);


  
            return "rentalConfirmation";
        } else {
            
            model.addAttribute("errorMessage", "Sorry, this cat is not available for rent.");
            return "errorpage";
        }
    } catch (Exception e) {
        model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
        return "errorpage";
    }
}


  
    @GetMapping("/rentings")
    public String showRentings(Model model) {
       
        try {
           Iterable<Renting> rentings = rentingRepository.findAll();

        for(Renting renting : rentings) {
            Cat cat = renting.getCat();
            byte[] image = cat.getImage();

            if (image != null) {
                String base64Image = Base64.getEncoder().encodeToString(image);
                cat.setBase64Image(base64Image);
            }
        }

        model.addAttribute("rentings", rentings);

        return "rentings";
        
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to load rentings" + e.getMessage());
            return "errorpage";
        }
    }


    @GetMapping("/deleteRenting/{id}")
    public String deleteRenting(@PathVariable("id") Long id) {
        rentingRepository.deleteById(id);
        return "redirect:/rentings";
    }

    @GetMapping("/editRenting/{id}")
    public String showEditRenting(@PathVariable("id") Long id, Model model) {
        try {
            Renting renting = rentingRepository.findById(id).orElse(null);

            if (renting != null) {
                Cat cat = renting.getCat();

            byte[] image = cat.getImage();

            if (image != null) {
                String base64Image = Base64.getEncoder().encodeToString(image);
                cat.setBase64Image(base64Image);
            }

            renting.setCat(cat);

            model.addAttribute("renting", renting);
            return "editRenting";
            
            } else {
                model.addAttribute("errorMessage", "Renting doesn't exist" + id);
                return "errorpage";
            }
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to edit renting of" + id + e.getMessage());
            return "errorpage";
        }
    }


    @PostMapping("/editRenting/{id}")
    public String editRenting(@PathVariable("id") Long id, @Valid @ModelAttribute("renting") Renting updatedRenting, BindingResult bindingResult, Model model,  Principal principal) {
       try {

         if (bindingResult.hasErrors()) {
           return "editRenting";
        }
        
    
        Renting existingRenting = rentingRepository.findById(id).orElse(null);

        if (existingRenting != null) {
            existingRenting.setRentalDate(updatedRenting.getRentalDate());
            existingRenting.setRentalDuration(updatedRenting.getRentalDuration());
       
            rentingRepository.save(existingRenting);

            return "redirect:/rentings"; 
        } else {
            model.addAttribute("errorMessage", "Rent doesn't exist" + id);
            return "errorpage";
        }
       } catch (Exception e) {
        model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
        return "errorpage";
    }

    }


}
