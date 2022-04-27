package lt.simzim.sportsclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.simzim.sportsclub.entities.Client;
import lt.simzim.sportsclub.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("clients", clientService.getClients());
		return "client_lis";
	}

	@GetMapping("/new")
	public String clientNew(Model model) {
		model.addAttribute("client", new Client());
		return "client_new";
	}

	@PostMapping("/new")
	public String addClient(
			@Valid 
			@ModelAttribute Client client, 
			BindingResult result, 
			Model model) {

		if (result.hasErrors()) {
			System.out.println("Pagavau klaida");
			model.addAttribute("client", new Client());
			return "client_new";
		}
		clientService.addClient(client);
		return "redirect:/client/";
	}

	@GetMapping("/update/{id}")
	public String clientNew(
			@PathVariable("id") Integer id, 
			Model model) {
		model.addAttribute("client", clientService.getClient(id));
		return "client_update";
	}

	@PostMapping("/update/{id}")
	public String clientUpdate(
			@PathVariable("id") Integer id, 
			@ModelAttribute Client c) {
		clientService.updateClient(c);
		return "redirect:/client/";
	}

	@GetMapping("/delete/{id}")
	public String groupDelete(@PathVariable("id") Integer id) {
		clientService.deleteClient(id);
		return "redirect:/client/";
	}

}
