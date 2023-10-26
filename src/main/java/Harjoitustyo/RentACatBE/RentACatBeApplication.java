package Harjoitustyo.RentACatBE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;



@SpringBootApplication
public class RentACatBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACatBeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner listOfCats(CatRepository repository) {
		return (args) -> {
			System.out.println("Hardcode list of cats");
		

			// TODO: Save mock data with city : 5
			repository.save(new Cat("Miri", "Scottish long hair", "Mirva", "pallo"));
			repository.save(new Cat("Musti", "Persian", "Pekka", "naru"));
			repository.save(new Cat("Molla", "Thai Siamese", "Vilma", "Hiiri"));

			
		
			for (Cat cat : repository.findAll()) {
				System.out.println(cat.toString());
			}
		
		};
		
	}

}
