package Harjoitustyo.RentACatBE.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.validation.constraints.NotNull;


public class RentingForm {
    private Long catId;

	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalDate = new Date();

	@NotNull
    private int rentalDuration = 1;

    public RentingForm() {
        super();
    }
	
  public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
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
	
}
