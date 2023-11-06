package Harjoitustyo.RentACatBE.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Harjoitustyo.RentACatBE.domain.Renting;
import Harjoitustyo.RentACatBE.domain.RentingRepository;

@RestController
public class RestRentingController {

    @Autowired
    RentingRepository repository;

    @GetMapping("/rents")
	public List<Renting> restRentList(){
	return (List<Renting>) repository.findAll();
	}
	
	@GetMapping("/rents/{id}")
	public Optional<Renting> restFindRent(@PathVariable("id") Long id) {
	return repository.findById(id);
	}
	
	@PostMapping("/rentrest")
	@ResponseStatus(HttpStatus.CREATED)
	Renting newRent(@RequestBody Renting newRent) {
		return repository.save(newRent);
	}
	

	@DeleteMapping("/rentrest/{id}")
	public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
	    if (repository.existsById(id)) {
	        repository.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
    
}
