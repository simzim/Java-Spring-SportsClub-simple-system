package lt.simzim.sportsclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.sportsclub.entities.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

}
