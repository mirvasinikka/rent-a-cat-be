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

import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;
import Harjoitustyo.RentACatBE.domain.CityRepository;

import jakarta.validation.Valid;

@Controller
public class CatController {
	
	@Autowired
	private CatRepository repository;
	
	@Autowired
    private CityRepository crepository;


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
		model.addAttribute("citys", crepository.findAll());
		model.addAttribute("cat", new Cat());
		
		return "add";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("cat") Cat cat, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("citys", crepository.findAll());
			return "edit";
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
			model.addAttribute("citys", crepository.findAll());
	    	return "edit";
	    }
	    
	   
}
