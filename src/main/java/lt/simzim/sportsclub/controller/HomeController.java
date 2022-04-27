package lt.simzim.sportsclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lt.simzim.sportsclub.services.ClientService;
import lt.simzim.sportsclub.services.RegistrationService;
import lt.simzim.sportsclub.services.WorkoutService;

@Controller
public class HomeController {

	@Autowired
	WorkoutService workoutService;

	@Autowired
	RegistrationService registrationService;

	@Autowired
	ClientService clientService;

	@GetMapping("/")
	public String home() {

		return "home";
	}

}