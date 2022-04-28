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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrementas db
	private Integer id;

	@Column(nullable = false, length = 64)
	@NotNull(message = "Vardas privalomas")
	@Length(min = 3, max = 64, message = "Vardas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 64 simbolius")
	private String name;

	@Column(nullable = false, length = 64)
	@NotNull(message = "Pavardė privaloma")
	@Length(min = 3, max = 64, message = "Pavardė turi būti ilgesnė nei 3 simboliai ir trumpesnis už 64 simbolius")
	private String surname;

	@Column(nullable = false)
	@NotNull(message = "El. pa6tas privalomas")
	@Email(message = "Netinkamas el. pašto formatas")
	private String email;

	@Column(nullable = false)
	@NotNull(message = "Telefono numeris privalomas")
	private String phone;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<Registration> registrations;

	public Client(String name, String surname, String email, String phone) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
	}

	public Client() {

	}

	public Client(String name, String surname) {
		this.name = name;
		this.surname = surname;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

}
