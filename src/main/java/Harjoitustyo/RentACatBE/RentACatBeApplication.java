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
	public CommandLineRunner listOfCats(CatRepository catRepository, AddressRepository addressRepository, AppUserRepository userRepository) {
		return (args) -> {

			Address address1 = new Address("katu", "Helsinki", "00940");
			Address address2 = new Address("kuja", "Vantaa", "05550");
			Address address3 = new Address("katu", "Espoo", "03330");

	

			AppUser user1 = new AppUser("user","mikko.mallika", "$2a$10$XPMxMF40UOfpo1jxytJ8g.B3uEA9VfqvqHa/WBqTI.HpUew3uzlEa", "USER", "Mikko", "Mallikas");
			AppUser user2 = new AppUser("admin", "malla.mollamaija", "$2a$10$M8I15ZmXIBC2tFTBP/cdR.//tCZ51EXm34tA2/Q/xqyGL2HYyYY9i", "ADMIN","Malla", "Mollamaija");

			user1.setAddress(address1);
			user2.setAddress(address3);

			userRepository.save(user1);
            userRepository.save(user2);


		    addressRepository.save(address1);
			addressRepository.save(address2);
			addressRepository.save(address3);

			Cat cat1 = new Cat("Miri", "Scottish long hair", "pallo", true);
			Cat cat2 = new Cat("Musti", "Persian", "naru", true);
			Cat cat3 = new Cat("Molla", "Thai Siamese", "Hiiri", true);

			cat1.setAddress(address1);
			cat2.setAddress(address2);
			cat3.setAddress(address3);

			cat1.setUser(user1);
			cat2.setUser(user2);
			cat3.setUser(user1);
			

			catRepository.save(cat1);
            catRepository.save(cat2);
            catRepository.save(cat3);
            
            

            
			for (Cat cat : catRepository.findAll()) {
				System.out.println(cat.toString());
			}

		    for (Address address : addressRepository.findAll()) {
				System.out.println(address.toString());
			}

			for (AppUser user : userRepository.findAll()) {
				System.out.println(user.toString());
			}
					
		};
		
	}

}
