package Harjoitustyo.RentACatBE.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RentingRepository extends CrudRepository<Renting, Long>{
	
	List<Renting> findByRentalDate(Date rentalDate);

}
