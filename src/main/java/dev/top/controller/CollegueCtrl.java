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
import dev.top.entities.CollegueApi;
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

	@GetMapping(value = "{matricule}")
	public CollegueApi getPersonne(@PathVariable String matricule) {
		List<Collegue> collegues = findAll();
		for (Collegue c : collegues) {
			if (c.getPseudo().equals(matricule)) {
			}
		}
		return null;
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
        final String url = "https://tommy-sjava.cleverapps.io/collegues?matricule=" + action.get("matricule");
        RestTemplate restTemplate = new RestTemplate();
        CollegueApi[] listeCollegue = restTemplate.getForObject(url, CollegueApi[].class);

        if (listeCollegue.length == 0) {
            System.out.println("Erreur à retourner");
        } else {

            CollegueApi collegueTrouvee = listeCollegue[0];
            System.out.println("eee"+collegueTrouvee.getPhoto());
            Collegue collegue = new Collegue(collegueTrouvee.getPrenom(), 0, collegueTrouvee.getPhoto());

            this.collegueRepo.save(collegue); // Enregistre le résultat du GetApi en BDD

        }
	}
}
