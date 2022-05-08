package lt.simzim.sportsclub.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "workouts")
public class Workout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 64)
	@Length(min = 3, max = 64, message = "Užsiėmimo pavadinimas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 64 simbolius")
	private String name;

	@Column(length = 10)
	private String date;

	@Column
	@Min(value = 1, message = "minimalus lankytojų skaičius 1 iki 100")
	@Max(value = 100, message = "maksimalus lankytojų skaičius 100")
	private Integer place;

	@Column
	@Length(min = 3, max = 64, message = " Užsiėmimo vietos pavadinimas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 64 simbolius")
	private String location;
	
	
	@Column
	private String fileName;
	

	@OneToMany(mappedBy = "workout", fetch = FetchType.EAGER)
	private List<Registration> registrations;

	public Workout() {

	}

	public Workout(String name, String date, Integer place, String location) {
		this.name = name;
		this.date = date;
		this.place = place;
		this.location = location;
	}

	
	public Workout(
			@Length(min = 3, max = 64, message = "Užsiėmimo pavadinimas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 64 simbolius") String name,
			String date,
			@Min(value = 1, message = "minimalus lankytojų skaičius 1 iki 100") @Max(value = 100, message = "maksimalus lankytojų skaičius 100") Integer place,
			@Length(min = 3, max = 64, message = " Užsiėmimo vietos pavadinimas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 64 simbolius") String location,
			String fileName) {
		
		this.name = name;
		this.date = date;
		this.place = place;
		this.location = location;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	@Override
	public String toString() {
		return "Workout [id=" + id + ", name=" + name + ", date=" + date + ", place=" + place + ", location=" + location
				+ ", registrations=" + registrations + "]";
	}

}
