package lt.simzim.sportsclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.sportsclub.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	Client findByUsername(String username); 
}
