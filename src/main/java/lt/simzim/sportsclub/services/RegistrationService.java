package lt.simzim.sportsclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.simzim.sportsclub.entities.Registration;
import lt.simzim.sportsclub.repositories.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	RegistrationRepository registrationRepository;

	public Registration addRegistration(Registration registration) {
		return registrationRepository.save(registration);
	}

	public List<Registration> getRegistrations() {
		return registrationRepository.findAll();
	}

	public Registration getRegistration(Integer id) {
		return registrationRepository.getById(id);
	}

	public void deleteRegistration(Integer Id) {
		registrationRepository.deleteById(Id);
	}

	public Registration updateRegistration(Registration registration) {

		Registration old = registrationRepository.getById(registration.getId());
		old.setClient(registration.getClient());
		old.setWorkout(registration.getWorkout());
		registrationRepository.save(old);
		return old;
	}

}
