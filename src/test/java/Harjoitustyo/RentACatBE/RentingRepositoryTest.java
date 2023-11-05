package Harjoitustyo.RentACatBE;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Harjoitustyo.RentACatBE.domain.AppUser;
import Harjoitustyo.RentACatBE.domain.AppUserRepository;
import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;
import Harjoitustyo.RentACatBE.domain.Renting;
import Harjoitustyo.RentACatBE.domain.RentingRepository;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RentingRepositoryTest {

    @Autowired
    private RentingRepository rentingRepository;
    @Autowired
     private CatRepository catRepository;

     @Autowired
	    private AppUserRepository userRepository; 

    @Test
	void findAllRentings() {
		Iterable<Renting> rent = rentingRepository.findAll();
		assertThat(rent).hasSize(1);
	}

    @Test
	void findByRentDate() throws ParseException {
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date rentalDate = dateFormat.parse("2024-01-01");

		Iterable<Renting> rent = rentingRepository.findByRentalDate(rentalDate);
		assertThat(rent).isNotNull();
	}

    @Test
	public void newRent() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date rentalDate = dateFormat.parse("2024-01-01");

        AppUser appUser = new AppUser();
	    userRepository.save(appUser);


        Cat cat = new Cat();
         cat.setName("Mau");
        cat.setBreed("Persian");
        cat.setToy("String");

        catRepository.save(cat);

		Renting rent = new Renting(appUser, cat, rentalDate, 4);
		rentingRepository.save(rent);
		assertThat(rent.getId()).isNotNull();

	}

    @Test
	public void editRent() throws ParseException {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date rentalDate = dateFormat.parse("2024-01-01");
        
		List<Renting> rent = rentingRepository.findByRentalDate(rentalDate);
		assertNotEquals(rent.get(0).getId(), null);

        Date rentalDateEdit = dateFormat.parse("2024-01-01");

		rent.get(0).setRentalDate(rentalDateEdit);
		List<Renting> rents = rentingRepository.findByRentalDate(rentalDateEdit);
		assertThat(rents).hasSize(1);
	}
}
