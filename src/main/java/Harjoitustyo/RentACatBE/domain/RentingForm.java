package Harjoitustyo.RentACatBE.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class RentingForm {
    @NotNull(message = "Cat id is required")
    private Long catId;

    @NotNull(message = "Rental date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Rental date must be in the present or future")
    private Date rentalDate = new Date();

    @NotNull(message = "Rental duration is required")
    @Min(value = 1, message = "Rental duration must be at least 1 day")
    @Max(value = 365, message = "Rental duration cannot exceed 365 days")
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
