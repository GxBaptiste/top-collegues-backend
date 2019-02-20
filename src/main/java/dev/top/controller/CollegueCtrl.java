package dev.top.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@RestController()
@RequestMapping("/collegues")
public class CollegueCtrl {

    @Autowired
    private CollegueRepo CollegueRepo;

    @GetMapping
    public List<Collegue> findAll() {
        return this.CollegueRepo.findAll();
    }
    
    @PatchMapping
    public void updateScore(Integer id, Integer score) {
    	this.CollegueRepo.findById(id).get().setScore(score);
    }
    
    @PatchMapping
    public void updatePseudo(Integer id, String pseudo) {
    	this.CollegueRepo.findById(id).get().setPseudo(pseudo);;
    }
    @PatchMapping
    public void updatePhotoUrl(Integer id, String photo) {
    	this.CollegueRepo.findById(id).get().setPhotoUrl(photo);
    }
}
