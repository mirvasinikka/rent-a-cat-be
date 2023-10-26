package Harjoitustyo.RentACatBE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;
import Harjoitustyo.RentACatBE.domain.City;
import Harjoitustyo.RentACatBE.domain.CityRepository;


@SpringBootApplication
public class RentACatBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACatBeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner listOfCats(CatRepository repository, CityRepository crepository) {
		return (args) -> {
			System.out.println("Hardcode list of cats");
		
			crepository.save(new City("Helsinki"));
            crepository.save(new City("Vantaa"));
            crepository.save(new City("Espoo"));
            crepository.save(new City("Tampere"));
            crepository.save(new City("Turku"));

			repository.save(new Cat("Miri", "Scottish long hair", "Mirva", "pallo", crepository.findByName("Helsinki").get(0)));
            repository.save(new Cat("Musti", "Persian", "Pekka", "naru", crepository.findByName("Espoo").get(0)));
            repository.save(new Cat("Molla", "Thai Siamese", "Vilma", "Hiiri", crepository.findByName("Turku").get(0)));
		
			for (Cat cat : repository.findAll()) {
				System.out.println(cat.toString());
			}
		
		};
		
	}

}
