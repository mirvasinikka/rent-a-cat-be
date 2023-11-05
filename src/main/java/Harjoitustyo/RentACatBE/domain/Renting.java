package Harjoitustyo.RentACatBE.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Renting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long renting_id;
	

	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser user; 

	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Cat cat; 

	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Rental date must be in the present or future")
	private Date rentalDate;

    @Min(value = 1, message = "Rental duration must be at least 1 day")
    @Max(value = 365, message = "Rental duration cannot exceed 365 days")
	private int rentalDuration;

	public Renting() {
		super();
	}
	
	public Renting(AppUser user, Cat cat, Date rentalDate, int rentalDuration) {
		super();
		this.user = user;
		this.cat = cat;
		this.rentalDate = rentalDate;
		this.rentalDuration = rentalDuration;
	}

	
     public Long getId() {
        return renting_id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }
	
	@Override
	public String toString() {
    return "Renting{" +
           "id=" + renting_id +
           ", user=" + user.getUsername() + 
           ", cat=" + cat.getName() + 
           ", rentalDate=" + rentalDate +
           ", rentalDuration=" + rentalDuration +
           '}';
}
    

}
