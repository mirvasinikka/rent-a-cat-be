package Harjoitustyo.RentACatBE.domain;


import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Cats")
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cat_id;
	
	@NotBlank( message = "Please provide cats name")
	@Size (min=2, max=20, message = "Name must be between 2 and 20 characters")
	private String name;
	
	@NotBlank(message = "Please provide cats breed")
    @Size(min = 3, max = 30, message = "Breed must be between 3 and 30 characters")
	private String breed;

	@Lob
	@Column(name = "image")
	private byte[] image;

    @Transient
    private String base64Image;


	@NotBlank(message = "Please tell us the cats favourite toy")
	@Size (min=2, max=20, message = "Favorite toy must be between 2 and 20 characters")
	private String toy;


	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser user;


	@ManyToOne
	@JoinColumn(name = "address_id")
	@Valid
    private Address address;

	@OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
	private List<Renting> rentings;



	public Cat() {
		super();
	}


	public Cat(String name, String breed, String toy, byte[] image) {
		super();
		this.name = name;
		this.breed = breed;
		this.toy = toy;
		this.image = image;
	}

	public Cat(String name, String breed, String toy, AppUser user, byte[] image) {
        this.name = name;
        this.breed = breed;
	    this.toy = toy;
        this.user = user;
		this.image = image;
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

	public byte[] getImage() {
		return image;
	}	

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getBase64Image() {
		return base64Image;
	}
 
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	@Override
	public String toString() {
		return "Cat [cat_id=" + cat_id + ", name=" + name + ", breed=" + breed + ", toy=" + toy + ", user=" + user
				+ ", address=" + address + ", rentings=" + rentings + ", image=" + image + ", base64Image="
				+ base64Image + "]";
	}
}
