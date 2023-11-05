package Harjoitustyo.RentACatBE.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Addresses")
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;
	
	@NotNull(message = "Street is required")
	@Size(min=5, max=255, message = "Street must be at least 5 characters")
    private String street;

	@NotNull(message = "City is required")
	@Size(min=5, max=100, message = "City must be at least 5 characters")
    private String city;

	@NotNull(message = "Post code is required")
	@Pattern(regexp = "[0-9]{5}", message = "Post code must be 5 digits")
    private String postCode;
    
    @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	private List<Cat> cats;
    
    @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	private List<AppUser> users;
    
    
	public Address() {
		super();
	}


	public Address(String street, String city, String postCode) {
		this.street = street;
		this.city = city;
		this.postCode = postCode;
	}


	public Long getAddress_id() {
		return address_id;
	}


	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostCode() {
		return postCode;
	}


	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", street=" + street + ", city=" + city + ", postCode=" + postCode
				+ "]";
	}
    
    
}
