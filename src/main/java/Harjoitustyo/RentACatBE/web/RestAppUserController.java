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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Harjoitustyo.RentACatBE.domain.AppUser;
import Harjoitustyo.RentACatBE.domain.AppUserRepository;

@RestController
public class RestAppUserController {
	
	@Autowired
	private AppUserRepository repository;
	
	@GetMapping("/userlist") 
	public List<AppUser> restUserList() {
		return (List<AppUser>) repository.findAll();
	}

	@GetMapping("/userlist/{id}")
	public Optional<AppUser> restFindUser(@PathVariable("id") Long id) {
	return repository.findById(id);
	}
	
	@PostMapping ("/userlist")
	AppUser newUser(@RequestBody AppUser newUser) {
		return repository.save(newUser);
	}
	
	@PutMapping("/userlist/{id}")
	AppUser editUser(@RequestBody AppUser editedUser, @PathVariable Long id) {
		editedUser.setId(id);
		return repository.save(editedUser);
	}

	@DeleteMapping("/userlist/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	    if (repository.existsById(id)) {
	        repository.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
