package lt.simzim.sportsclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lt.simzim.sportsclub.entities.Workout;
import lt.simzim.sportsclub.services.FileStorageService;
import lt.simzim.sportsclub.services.WorkoutService;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

	@Autowired
	WorkoutService workoutService;
	
	@Autowired
	FileStorageService fileStorage;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("workouts", workoutService.getWorkouts());
		return "workout_lis";
	}

	@GetMapping("/new")
	public String workoutNew(Model model) {
		model.addAttribute("workout", new Workout());
		return "workout_new";
	}

	@PostMapping("/new")
	public String addWorkout(
			@Valid
			@ModelAttribute Workout workout, 
			BindingResult result, 
			@RequestParam("agreement") MultipartFile agreement,
			Model model) {
		
		if (result.hasErrors()) {
			System.out.println("Pagavau klaida");
		return "workout_new";
		}
		
		workout.setFileName(agreement.getOriginalFilename());
		workout = workoutService.addWorkout(workout);
			
		fileStorage.store(agreement,workout.getId().toString());
		
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
	public String workoutUpdate(
			@Valid 
			@ModelAttribute Workout w,
			BindingResult result, 
			@PathVariable("id") Integer id
			) {
		
		if (result.hasErrors()) {
			System.out.println("Pagavau klaida");
			return "workout_update";
		}
			
		workoutService.updateWorkout(w);
		return "redirect:/workout/";
	}

	@GetMapping("/delete/{id}")
	public String workoutDelete(@PathVariable("id") Integer id) {
		workoutService.deleteWorkout(id);
		return "redirect:/workout/";
	}
	
	@GetMapping("/agreement/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getAgreement(@PathVariable Integer id){
		Resource file = fileStorage.loadFile(id.toString());
		Workout w = workoutService.getWorkout(id);
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ w.getFileName()+"\"")
				.body(file);
	}

}
