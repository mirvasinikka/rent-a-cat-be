package Harjoitustyo.RentACatBE.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;

	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "firstName", nullable = false)
	private String fName;
	
	@Column(name = "lastName", nullable = false)
	private String lName;
	
	@Column(name = "email", nullable = false)
	private String email;

	public User() {
		super();
	}
	

	public User(String username, String password, String role, String city, String fName, String lName,
			String email) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.city = city;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
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




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", role=" + role + ", city=" + city
				+ ", fName=" + fName + ", lName=" + lName + ", email=" + email + "]";
	}

}
