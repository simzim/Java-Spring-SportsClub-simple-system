package lt.simzim.sportsclub.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name = "clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // autoincrementas db
	private Integer id;
	
	@Column(nullable = false, length = 64)
	private String name;
	
	@Column(nullable = false, length = 64) 
	private String surname;
	
	@Column
	private String email;
	
	
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

	@Column
	private String phone;
	
	
	
	


}
