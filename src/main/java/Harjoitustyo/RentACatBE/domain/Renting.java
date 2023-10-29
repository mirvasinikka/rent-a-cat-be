package Harjoitustyo.RentACatBE.domain;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Renting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long renting_id;
	

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; 

	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Cat cat; 

	@Temporal(TemporalType.TIMESTAMP)
	private Date rentalDate;

	private int rentalDuration;

	public Renting() {
		super();
	}
	
	public Renting(User user, Cat cat, Date rentalDate, int rentalDuration) {
		super();
		this.user = user;
		this.cat = cat;
		this.rentalDate = rentalDate;
		this.rentalDuration = rentalDuration;
	}

	
     public Long getId() {
        return renting_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
