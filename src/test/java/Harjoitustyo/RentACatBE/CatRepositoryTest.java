package Harjoitustyo.RentACatBE;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Harjoitustyo.RentACatBE.domain.Cat;
import Harjoitustyo.RentACatBE.domain.CatRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CatRepositoryTest {
	
	@Autowired
    private CatRepository catRepository;

    @Test
    void saveCat() {
        Cat cat = new Cat();
        cat.setName("Mau");
        cat.setBreed("Persian");
        cat.setToy("String");

        Cat savedCat = catRepository.save(cat);
        assertNotNull(savedCat);

        assertThat(savedCat.getId()).isNotNull();
        assertThat(savedCat.getName()).isEqualTo("Mau");
        assertThat(savedCat.getBreed()).isEqualTo("Persian");
        assertThat(savedCat.getToy()).isEqualTo("String");

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
        cat.setName("testi");

        catRepository.save(cat);
        
        List<Cat> updatedCats = catRepository.findByName("testi");
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
