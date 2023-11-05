package Harjoitustyo.RentACatBE.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterForm {
	
    @NotNull(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min=2, max=50, message = "Password must be between 2 and 50 characters")
    private String password;

    @NotEmpty(message = "Password Check is required")
    @Size(min=2, max=50, message = "Password Check must be between 2 and 50 characters")
    private String passwordCheck;
    
    
    @NotNull(message = "First name is required")
    @Size(min = 3, max = 50, message = "First name must must be between 3 and 50 characters")
    private String firstName;
    
    @NotNull(message = "Last name is required")
     @Size(min = 3, max = 50, message = "Last name must must be between 3 and 50 characters")
    private String lastName;
    
    @NotNull(message = "Email is required")
    @Email(message = "Email must be a valid email address")
	@Size(min=3, max=50, message = "Email must be between 3 and 50 characters")
    private String email;

    @NotEmpty
    private String role = "USER";

	@Valid
	private Address address = new Address();
    

	public RegisterForm() {
		super();
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

	public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

	@Override
	public String toString() {
		return "RegisterForm [username=" + username + ", password=" + password + ", passwordCheck=" + passwordCheck
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", role=" + role
				+ ", address=" + address + "]";
	}
    
}
