package Harjoitustyo.RentACatBE.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface CatRepository extends CrudRepository<Cat, Long> {
	
	List<Cat> findByName(String name);

}
