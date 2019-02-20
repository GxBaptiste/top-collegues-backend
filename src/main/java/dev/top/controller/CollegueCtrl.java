package dev.top.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@RestController()
@RequestMapping("/collegues")
public class CollegueCtrl {

    @Autowired
    private CollegueRepo collegueRepo;

    @GetMapping
    public List<Collegue> findAll() {
        return this.collegueRepo.findAll();
    }
    
    @PatchMapping("/score")
    public void updateScore(Integer id, Integer score) {
    	this.collegueRepo.findById(id).get().setScore(score);
    }
    
    @PatchMapping(value = "{pseudo}")
    public void updatePseudo(@PathVariable String pseudo, @RequestBody Map<String,String> action) {
    	List<Collegue> collegues = findAll();
    	for(Collegue c : collegues) {
    		System.out.println(action.get("action"));
    		if(c.getPseudo().equals(pseudo)) {
    			if(action.get("action").equals("AIMER"))
    				c.setScore(c.getScore()+10);
    			else
    				c.setScore(c.getScore()-5);
    			this.collegueRepo.save(c);
    			break;
    		}
    	}	
    }
    
    @PatchMapping("/photo")
    public void updatePhotoUrl(Integer id, String photo) {
    	this.collegueRepo.findById(id).get().setPhotoUrl(photo);
    }
}
