package Harjoitustyo.RentACatBE;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Harjoitustyo.RentACatBE.domain.Address;
import Harjoitustyo.RentACatBE.domain.AddressRepository;
import Harjoitustyo.RentACatBE.domain.User;
import Harjoitustyo.RentACatBE.domain.UserRepository;
import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;




@SpringBootApplication
public class RentACatBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACatBeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner listOfCats(CatRepository catRepository, AddressRepository addressRepository, UserRepository userRepository) {
		return (args) -> {

			Address address1 = new Address("katu", "Helsinki", "00940");
			Address address2 = new Address("kuja", "Vantaa", "05550");
			Address address3 = new Address("katu", "Espoo", "03330");

			Cat cat1 = new Cat("Miri", "Scottish long hair", "Mirva", "pallo", address1);
			Cat cat2 = new Cat("Musti", "Persian", "Pekka", "naru", address3);
			Cat cat3 = new Cat("Molla", "Thai Siamese", "Vilma", "Hiiri", address2);

			User user1 = new User("user", "$2a$10$XPMxMF40UOfpo1jxytJ8g.B3uEA9VfqvqHa/WBqTI.HpUew3uzlEa", "USER", "Mikko", "Mallikas", "mikko.mallika", address1);
			User user2 = new User("admin", "$2a$10$M8I15ZmXIBC2tFTBP/cdR.//tCZ51EXm34tA2/Q/xqyGL2HYyYY9i",  "ADMIN","Malla", "Mollamaija", "malla.mollamaija", address2);

			List<Cat> catList = new ArrayList<Cat>();
			List<User> userList = new ArrayList<User>();
			
		
			addressRepository.save(address1);
			addressRepository.save(address2);
			addressRepository.save(address3);

			catRepository.save(cat1);
            catRepository.save(cat2);
            catRepository.save(cat3);
            
            userRepository.save(user1);
            userRepository.save(user2);
            
            catList.add(cat1);
			userList.add(user1);
	

            
			for (Cat cat : catRepository.findAll()) {
				System.out.println(cat.toString());
			}

		    for (Address address : addressRepository.findAll()) {
				System.out.println(address.toString());
			}

			for (User user : userRepository.findAll()) {
				System.out.println(user.toString());
			}
					
		};
		
	}

}
