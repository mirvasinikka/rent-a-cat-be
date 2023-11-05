package Harjoitustyo.RentACatBE;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Harjoitustyo.RentACatBE.domain.Address;
import Harjoitustyo.RentACatBE.domain.AddressRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {
    
    @Autowired
    private AddressRepository addressRepository;

    @Test
	void findAllAddress() {
		Iterable<Address> address = addressRepository.findAll();
		assertThat(address).isNotEmpty();
		assertThat(address).hasSize(3);
	}

    @Test
	void findByTheCity() {
		Iterable<Address> address = addressRepository.findByCity("Helsinki");
		assertThat(address).isNotNull();
	}

    @Test
	public void newAddress() {
		Address address = new Address("srteet", "Helsinki", "00580");
		addressRepository.save(address);
		assertThat(address.getAddress_id()).isNotNull();
		assertThat(address.getCity()).isEqualTo("Helsinki");
		assertThat(address.getStreet()).isEqualTo("srteet");
		assertThat(address.getPostCode()).isEqualTo("00580");
	}

    @Test
	public void updateAddress() {
		List<Address> address = addressRepository.findByCity("Helsinki");
		assertNotEquals(address.get(0).getAddress_id(), null);
		address.get(0).setCity("Oulu");
		List<Address> addreses = addressRepository.findByCity("Oulu");
		assertThat(addreses).hasSize(1);
	}
}
