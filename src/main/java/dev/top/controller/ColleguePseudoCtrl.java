package dev.top.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.CollegueApi;
import dev.top.repos.CollegueApiRepo;

@RestController()
@CrossOrigin
@RequestMapping("/collegues/api")
public class ColleguePseudoCtrl {

	@Autowired
	private CollegueApiRepo collegueApiRepo;

	@GetMapping
	public List<CollegueApi> findAll() {
		return this.collegueApiRepo.findAll();
	}

	@GetMapping(value = "{matricule}")
	public CollegueApi getPersonneByMatricule(@PathVariable String matricule) {
		for(CollegueApi c : this.collegueApiRepo.findAll()) {
			if(c.getMatricule().equals(matricule)) {
				return c;
			}
		}
		return new CollegueApi("?","?","?","?","?","?","?",0);
	}
}
