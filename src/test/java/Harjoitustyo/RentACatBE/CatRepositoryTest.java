package Harjoitustyo.RentACatBE;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;

@DataJpaTest
public class CatRepositoryTest {
	
	@Autowired
    private CatRepository catRepository;

    @Test
    void saveCat() {
        Cat cat = new Cat();
        Cat savedCat = catRepository.save(cat);
        assertThat(savedCat).isNotNull();
    }

    @Test
    void findCatById() {
        Long catId = 1L;
        Optional<Cat> cat = catRepository.findById(catId);
        assertThat(cat).isPresent();
    }

    @Test
    void findAllCats() {
        List<Cat> cats = (List<Cat>) catRepository.findAll();
        assertThat(cats).isNotEmpty();
    }

    @Test
    void updateCat() {
        Long catId = 1L; 
        Optional<Cat> optionalCat = catRepository.findById(catId);
        assertTrue(optionalCat.isPresent());
        
        Cat cat = optionalCat.get();
        cat.setName("musti");

        catRepository.save(cat);
        
        List<Cat> updatedCats = catRepository.findByName("musti");
        assertThat(updatedCats).hasSize(1);
    }

    @Test
    void deleteCat() {
        Long catId = 1L;
        catRepository.deleteById(catId);
        Optional<Cat> cat = catRepository.findById(catId);
        assertThat(cat).isEmpty();
    }

}
