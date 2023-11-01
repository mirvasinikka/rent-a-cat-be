package Harjoitustyo.RentACatBE.domain;


import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Cats")
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cat_id;
	
	@NotBlank( message = "Please provide cats name")
	@Size (min=2, max=10)
	private String name;
	
	@NotBlank(message = "Please provide cats breed")
	@Size (min=3, max=30)
	private String breed;

	private boolean available = true;
	

	@NotBlank(message = "Please tell us the cats favourite toy")
	@Size (min=2, max=20)
	private String toy;


	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser user;


	@ManyToOne
	@JoinColumn(name = "address_id")
    private Address address;

	@OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
	private List<Renting> rentings;



	public Cat() {
		super();
	}


	public Cat(String name, String breed, String toy, boolean available) {
		super();
		this.name = name;
		this.breed = breed;
		this.toy = toy;
		this.available = available;
	}

	public Cat(String name, String breed, String toy, boolean available,  AppUser user) {
        this.name = name;
        this.breed = breed;
	    this.toy = toy;
        this.available = available;
        this.user = user;
    }


	public Long getId() {
		return cat_id;
	}

  	public void setId(Long cat_id) {
        this.cat_id = cat_id;
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


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

	public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


	public String getToy() {
		return toy;
	}

	public void setToy(String toy) {
		this.toy = toy;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}


	 @Override
    public String toString() {
        return "Cat{" +
                "cat_id=" + cat_id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
				", toy='" + toy + '\'' +
                ", available=" + available +
				", user=" + user +
				", city=" + address.getCity() +
                '}';
    }


	

}
