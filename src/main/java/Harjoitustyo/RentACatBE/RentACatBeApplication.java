package Harjoitustyo.RentACatBE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Harjoitustyo.RentACatBE.domain.Address;
import Harjoitustyo.RentACatBE.domain.AddressRepository;
import Harjoitustyo.RentACatBE.domain.AppUser;
import Harjoitustyo.RentACatBE.domain.AppUserRepository;
import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;



@SpringBootApplication
public class RentACatBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACatBeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner listOfCats(CatRepository repository, AddressRepository arepository) {
		return (args) -> {
			System.out.println("Hardcode list of cats");
			
			arepository.save(new Address("katu", "Helsinki", "00940"));
			arepository.save(new Address("kuja", "Vantaa", "05550"));
			arepository.save(new Address("katu", "Espoo", "03330"));

			repository.save(new Cat("Miri", "Scottish long hair", "Mirva", "pallo", arepository.findByCity("Helsinki").get(0)));
            repository.save(new Cat("Musti", "Persian", "Pekka", "naru", arepository.findByCity("Espoo").get(0)));
            repository.save(new Cat("Molla", "Thai Siamese", "Vilma", "Hiiri", arepository.findByCity("Vantaa").get(0)));
		
            
        
            
            
			for (Cat cat : repository.findAll()) {
				System.out.println(cat.toString());
			}
		
		};
		
	}

}
