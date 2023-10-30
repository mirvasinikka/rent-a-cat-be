package Harjoitustyo.RentACatBE.domain;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;




@Entity
@Table(name="AppUser")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long user_id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;

    @Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;

	
	@Column(name = "fName", nullable = false)
	private String fName;
	
	@Column(name = "lName", nullable = false)
	private String lName;


	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Renting> rentings;


	@Column(name = "role")
	private String role;
	

	

	public AppUser() {
		super();
	}
	

	public AppUser(String username,  String email, String password, String role, String fName, String lName) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.fName = fName;
		this.lName = lName;
        this.email = email;
	}



    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public List<Renting> getrentings() {
        return rentings;
    }

    public void setrentings(List<Renting> rentings) {
        this.rentings = rentings;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


	@Override
	public String toString() {
		return "AppUser [username=" + username + ", password=" + password + ", role=" + role
				+ ", fName=" + fName + ", lName=" + lName + ", email=" + email + "]";
	}

}
