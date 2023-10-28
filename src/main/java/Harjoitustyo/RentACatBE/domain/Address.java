package Harjoitustyo.RentACatBE.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;
	
    private String street;
    

    private String city;
    
    private String postCode;
    
    @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	private List<Cat> cats;
    
	public Address() {
		super();
		// TODO Auto-generated constructor stub
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
