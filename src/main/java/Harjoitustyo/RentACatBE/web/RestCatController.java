package Harjoitustyo.RentACatBE.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;

@RestController
public class RestCatController {
	
	@Autowired
	private CatRepository repository;
	
	@GetMapping("/cats")
	public List<Cat> restCatList(){
	return (List<Cat>) repository.findAll();
	}
	
	@GetMapping("/cat/{id}")
	public Optional<Cat> restFindCat(@PathVariable("id") Long id) {
	return repository.findById(id);
	}
	
	@PostMapping("/cat")
	Cat newCat(@RequestBody Cat newCat) {
		return repository.save(newCat);
	}
	
	@PutMapping("/cat/{id}")
	Cat editCat(@RequestBody Cat editedCat, @PathVariable Long id) {
		editedCat.setId(id);
		return repository.save(editedCat);
	}

}
