package lt.simzim.sportsclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lt.simzim.sportsclub.services.FileStorageService;

@Controller
@RequestMapping("/files")
public class FileUploadController {
	
	@Autowired
	FileStorageService storageService;
	
	@GetMapping("/")
	public String home() {
		return "upload.html";
	}
	
	@PostMapping("/")
	public String upload(@RequestParam("file") MultipartFile file) {
		storageService.store(file);
		return home();
	}
	
	
		
}
