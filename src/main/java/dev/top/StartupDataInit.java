package dev.top;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.top.controller.CollegueCtrl;
import dev.top.entities.Collegue;
import dev.top.entities.Version;
import dev.top.repos.CollegueRepo;
import dev.top.repos.VersionRepo;

@Component
public class StartupDataInit {

    @Autowired
    VersionRepo versionRepo;
    
    @Autowired
    CollegueRepo collegueRepo;
    

    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        this.versionRepo.save(new Version("v1"));
        this.versionRepo.save(new Version("v2"));
        this.versionRepo.save(new Version("v3"));
        this.versionRepo.save(new Version("v4"));
        
        Collegue c1 = new Collegue("gon",0,"test1");
        Collegue c2 = new Collegue("killua",0,"test2");
        Collegue c3 = new Collegue("leolio",0,"test3");
        this.collegueRepo.save(c1);
        this.collegueRepo.save(c2);
        this.collegueRepo.save(c3);
        
    }
}
