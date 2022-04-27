package lt.simzim.sportsclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.simzim.sportsclub.entities.Workout;
import lt.simzim.sportsclub.services.WorkoutService;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

	@Autowired
	WorkoutService workoutService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("workouts", workoutService.getWorkouts());
		return "workout_lis";
	}

	@GetMapping("/new")
	public String workoutNew(Model model) {
		return "workout_new";
	}

	@PostMapping("/new")
	public String addWorkout(
			@RequestParam("name") String name, 
			@RequestParam("date") String date,
			@RequestParam("place") Integer place, 
			@RequestParam("location") String location) {
		Workout w = new Workout(name, date, place, location);
		workoutService.addWorkout(w);
		return "redirect:/workout/";
	}

	@GetMapping("/update/{id}")
	public String workoutNew(
			@PathVariable("id") Integer id, 
			Model model) {
		model.addAttribute("workout", workoutService.getWorkout(id));
		return "workout_update";
	}

	@PostMapping("/update/{id}")
	public String workoutUpdate(@PathVariable("id") Integer id, Workout w) {
		workoutService.updateWorkout(w);
		return "redirect:/workout/";
	}

	@GetMapping("/delete/{id}")
	public String workoutDelete(@PathVariable("id") Integer id) {
		workoutService.deleteWorkout(id);
		return "redirect:/workout/";
	}

}
