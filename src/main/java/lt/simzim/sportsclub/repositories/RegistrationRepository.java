package lt.simzim.sportsclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.sportsclub.entities.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

}
