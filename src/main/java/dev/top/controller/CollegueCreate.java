package dev.top.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@RestController()
@CrossOrigin
@RequestMapping("/collegues/create")
public class CollegueCreate {

	@Autowired
	private CollegueRepo collegueRepo;

	@PostMapping
	public void postCollegue(@RequestBody Map<String, String> action) {
		System.out.println(this.collegueRepo.findAll().size());
		Collegue c = new Collegue(action.get("pseudo"), 0, action.get("URLImage"));
		this.collegueRepo.save(c);
		System.out.println(this.collegueRepo.findAll().size());
	}
}
