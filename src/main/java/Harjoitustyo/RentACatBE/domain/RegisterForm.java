package Harjoitustyo.RentACatBE.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterForm {
	
	@NotEmpty
    @Size(min=3, max=30)
    private String username = "";

    @NotEmpty
    @Size(min=3, max=50)
    private String password = "";

    @NotEmpty
    @Size(min=3, max=50)
    private String passwordCheck = "";
    
    @NotEmpty
    @Size(min=3, max=20)
    private String city = "";
    
    @NotEmpty
    @Size(min=3, max=50)
    private String street = "";
    
    @NotEmpty
    @Size(min=3, max=10)
    private String postCode = "";
    
    @NotEmpty
    @Size(min=3, max=50)
    private String firstName = "";
    
    @NotEmpty
    @Size(min=3, max=50)
    private String lastName = "";
    
    @NotEmpty
    @Size(min=3, max=30)
    private String email = "";

    @NotEmpty
    private String role = "USER";
    
    

	public RegisterForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getPostCode() {
		return postCode;
	}



	public void setPostCode(String postCode) {
		this.postCode = postCode;
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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    

}
