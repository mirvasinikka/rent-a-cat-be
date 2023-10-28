package Harjoitustyo.RentACatBE.web;

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

import jakarta.validation.Valid;

@Controller
public class CatController {
	
	@Autowired
	private CatRepository repository;

    @Autowired
	private AddressRepository arepository;
	

	@RequestMapping("/main")
	@ResponseBody
	public String showMainPage() {
		return "Tämä on pääsivu :) jee";
	}
	
	@GetMapping("/list")
	public String catList(Model model) {
		model.addAttribute("cats", repository.findAll());
		return "list";
	}

	@GetMapping("/add")
	public String addCat(Model model) {
		Cat cat = new Cat(); 
		Address address = new Address(); 
		cat.setAddress(address); 
		model.addAttribute("cat", cat);
	
		
		return "add";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "edit";
		}
		
		// Get the address associated with the cat
	    Address address = cat.getAddress();


	    // Check if the address is new (doesn't have an ID)
	    if (address.getAddress_id() == null) {
	        arepository.save(address);
	    }

		
		repository.save(cat);
		return "redirect:/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteCat(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return "redirect:/list";
	}
	
	 @GetMapping("/edit/{id}")
	    public String editCat(@PathVariable("id") Long id, Model model) {
		 	repository.findById(id).orElse(new Cat());
	    	model.addAttribute("cat", repository.findById(id));
	    	model.addAttribute("address", arepository.findAll());
	    	return "edit";
	    }
	    
	   
}
