package Harjoitustyo.RentACatBE.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class City {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long cityid;
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
	private List<Cat> cats;

	public City(String name) {
		super();
		this.name = name;
	}

	public City() {
		super();
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [cityid=" + cityid + ", name=" + name + "]";
	}
	
	

}
