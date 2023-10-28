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
	public CommandLineRunner listOfCats(CatRepository repository, AddressRepository arepository, AppUserRepository urepository) {
		return (args) -> {
			System.out.println("Hardcode list of cats");
			
			arepository.save(new Address("katu", "Helsinki", "00940"));
			arepository.save(new Address("kuja", "Vantaa", "05550"));
			arepository.save(new Address("katu", "Espoo", "03330"));

			repository.save(new Cat("Miri", "Scottish long hair", "Mirva", "pallo", arepository.findByCity("Helsinki").get(0)));
            repository.save(new Cat("Musti", "Persian", "Pekka", "naru", arepository.findByCity("Espoo").get(0)));
            repository.save(new Cat("Molla", "Thai Siamese", "Vilma", "Hiiri", arepository.findByCity("Vantaa").get(0)));
            
            urepository.save(new AppUser("user", "$2a$10$XPMxMF40UOfpo1jxytJ8g.B3uEA9VfqvqHa/WBqTI.HpUew3uzlEa", "USER"));
            urepository.save(new AppUser("admin", "$2a$10$M8I15ZmXIBC2tFTBP/cdR.//tCZ51EXm34tA2/Q/xqyGL2HYyYY9i",  "ADMIN"));
            
            
			for (Cat cat : repository.findAll()) {
				System.out.println(cat.toString());
			}
		
		};
		
	}

}
