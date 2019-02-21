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
    CollegueRepo collegueRepo;
    

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        Collegue c1 = new Collegue("gon",0,"https://pm1.narvii.com/6827/59c5a5e6192c0c7f3f91cfd41bfb28d129d8b3dcv2_hq.jpg");
        Collegue c2 = new Collegue("killua",0,"https://pbs.twimg.com/profile_images/902127623303811072/lyRCwNfB_400x400.jpg");
        Collegue c3 = new Collegue("leolio",0,"https://pbs.twimg.com/profile_images/1029167552449183744/uUDVHZw3_400x400.jpg");
        Collegue c4 = new Collegue("kurapika",0,"https://sm1.narvii.com/6726/a40c93ddf714568dee291c80b8764d8e8afd165dv2_hq.jpg");
        Collegue c5 = new Collegue("hisoka",0,"https://pbs.twimg.com/profile_images/1089990918004981761/xAq1K0Z7_400x400.jpg");
        this.collegueRepo.save(c1);
        this.collegueRepo.save(c2);
        this.collegueRepo.save(c3);
        this.collegueRepo.save(c4);
        this.collegueRepo.save(c5);
        
    }
}
