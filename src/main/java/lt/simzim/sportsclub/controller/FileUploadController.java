package lt.simzim.sportsclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename){
		
		Resource file=storageService.loadFile(filename);
		if (file==null) {
			return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity
				.ok()
				// .header(HttpHeaders.CONTENT_TYPE, storageService.getContentType(filename)) 
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+filename+"\"")
				.body(file);
				//.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; ");
		
	}
	
		
}
