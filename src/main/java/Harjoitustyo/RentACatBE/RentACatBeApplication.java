package Harjoitustyo.RentACatBE;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



@SpringBootApplication
public class RentACatBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACatBeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner listOfCats(CatRepository catRepository) {
		return (args) -> {
		
            
			for (Cat cat : catRepository.findAll()) {
				try {
					byte[] imageData = Files.readAllBytes(Paths.get("src/main/resources/static/images/cat-" + cat.getId() + ".jpg"));
					cat.setImage(imageData);	
				} catch (IOException e) {
					System.out.println("Error reading image file: " + e.getMessage());
				}

				catRepository.save(cat);
				
			}
					
		};
		
	}

}
