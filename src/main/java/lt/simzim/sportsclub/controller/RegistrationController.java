package lt.simzim.sportsclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.simzim.sportsclub.entities.Registration;
import lt.simzim.sportsclub.services.ClientService;
import lt.simzim.sportsclub.services.RegistrationService;
import lt.simzim.sportsclub.services.WorkoutService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@Autowired
	WorkoutService workoutService;

	@Autowired
	ClientService clientService;

	@GetMapping("/")
	public String getRegistrations(Model model) {
		model.addAttribute("registrations", registrationService.getRegistrations());
		return "reg";
	}

	@GetMapping("/new")
	public String newRegistration(Model model) {
		System.out.println("NEW pirmas esam cia");
		model.addAttribute("registration", new Registration());
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("workouts", workoutService.getWorkouts());
		return "reg_new";

	}

	@PostMapping("/new")
	public String storeRegistation(
			@ModelAttribute Registration registration,
			@RequestParam("clientId") Integer clientId, 
			@RequestParam("workoutId") Integer workoutId, 
			Model model) {
		
		registration.setClient(clientService.getClient(clientId));
		registration.setWorkout(workoutService.getWorkout(workoutId));
		registrationService.addRegistration(registration);
		return "redirect:/registration/";
	}

	@GetMapping("/update/{id}")
	public String registrationNew(
			@PathVariable("id") Integer id, 
			Model model) {
		model.addAttribute("registration", registrationService.getRegistration(id));
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("workouts", workoutService.getWorkouts());
		return "reg_update";
	}

	@PostMapping("/update/{id}")
	public String registrationUpdate(
			@ModelAttribute Registration registration, 
			@PathVariable("id") Integer id,
			@RequestParam("clientId") Integer clientId, 
			@RequestParam("workoutId") Integer workoutId, 
			Model model) {

		registration.setClient(clientService.getClient(clientId));
		registration.setWorkout(workoutService.getWorkout(workoutId));
		registrationService.updateRegistration(registration);
		return "redirect:/registration/";
	}

	@GetMapping("/delete/{id}")
	public String registrationDelete(@PathVariable("id") Integer id) {
		registrationService.deleteRegistration(id);
		return "redirect:/registration/";
	}

}
