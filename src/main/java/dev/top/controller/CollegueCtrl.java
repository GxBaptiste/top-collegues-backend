package dev.top.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@RestController()
@CrossOrigin
@RequestMapping("/collegues")
public class CollegueCtrl {

	@Autowired
	private CollegueRepo collegueRepo;

	@GetMapping
	public List<Collegue> findAll() {
		return this.collegueRepo.findAll();
	}

	@PatchMapping(value = "{pseudo}")
	public Collegue updatePseudo(@PathVariable String pseudo, @RequestBody Map<String, String> action) {
		List<Collegue> collegues = findAll();
		for (Collegue c : collegues) {
			if (c.getPseudo().equals(pseudo)) {
				if (action.get("action").equals("AIMER"))
					c.setScore(c.getScore() + 1);
				else {
					if (action.get("action").equals("DETESTER"))
						c.setScore(c.getScore() - 1);
				}
				this.collegueRepo.save(c);
				return c;
			}
		}
		return null;
	}

	@PostMapping
	public void postCollegue(@RequestBody Map<String, String> action) {
		
		RestTemplate restTemplate = new RestTemplate();
		 
        // Send request with GET method and default Headers.
		CollegueApi[] aux = restTemplate.getForObject("https://tommy-sjava.cleverapps.io/collegues", CollegueApi[].class);
		String matricule = action.get("matricule");
		boolean aux2=false;
		for(CollegueApi collegueApi : aux) {
	        if(collegueApi.getMatricule().equals(matricule)) {
	        	Collegue c = new Collegue(action.get("pseudo"),0,action.get("URLImage"));
	        	this.collegueRepo.save(c);
	        	System.out.println("test1"+c.getPhotoUrl());
	        	aux2=true;
	        }
		}
		if(!aux2) {
			System.out.println(action);
        	Collegue c = new Collegue(action.get("pseudo"),0,action.get("URLImage"));
        	System.out.println("test2"+c.getPhotoUrl());
        	this.collegueRepo.save(c);
		}     
	}
}
