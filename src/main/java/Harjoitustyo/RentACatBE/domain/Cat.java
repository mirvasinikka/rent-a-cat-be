package Harjoitustyo.RentACatBE.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank( message = "Please provide cats name")
	@Size (min=2, max=10)
	private String name;
	
	@NotBlank(message = "Please provide cats breed")
	@Size (min=3, max=30)
	private String breed;
	
	@NotBlank(message = "Please provide owner")
	@Size (min=2, max=30)
	private String owner;
	
	@NotBlank(message = "Please tell us the cats favourite toy")
	@Size (min=2, max=20)
	private String toy;
	
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city")
    private City city;
	
	public Cat() {
		super();
	}


	public Cat(String name, String breed, String owner, String toy, City city) {
        super();
        this.name = name;
        this.breed = breed;
        this.owner = owner;
        this.toy = toy;
        this.city = city;
    }



	public Cat(String name, String breed, String owner, String toy) {
		super();
		this.name = name;
		this.breed = breed;
		this.owner = owner;
		this.toy = toy;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getBreed() {
		return breed;
	}




	public void setBreed(String breed) {
		this.breed = breed;
	}




	public String getOwner() {
		return owner;
	}




	public void setOwner(String owner) {
		this.owner = owner;
	}




	public String getToy() {
		return toy;
	}




	public void setToy(String toy) {
		this.toy = toy;
	}




	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}




	@Override
	public String toString() {
		return "Cat [id=" + id + ", name=" + name + ", breed=" + breed + ", owner=" + owner + ", toy=" + toy + "]";
	}
	
	
	
	

}
