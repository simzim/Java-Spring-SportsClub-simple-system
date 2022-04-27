package lt.simzim.sportsclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.simzim.sportsclub.entities.Workout;
import lt.simzim.sportsclub.repositories.WorkoutRepository;

@Service
public class WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;

	public Workout addWorkout(Workout workout) {
		return workoutRepository.save(workout);
	}

	public List<Workout> getWorkouts() {
		return workoutRepository.findAll();
	}

	public Workout getWorkout(Integer Id) {
		return workoutRepository.getById(Id);
	}

	public Workout updateWorkout(Workout workout) {
		Workout old = this.getWorkout(workout.getId());
		old.setName(workout.getName());
		old.setPlace(workout.getPlace());
		old.setLocation(workout.getLocation());
		workoutRepository.save(old);
		return old;
	}

	public void deleteWorkout(Integer id) {
		workoutRepository.deleteById(id);
	}

}
