package be.bstorm.akimts.rest.bxl.utils;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.service.impl.EnfantServiceImpl;
import be.bstorm.akimts.rest.bxl.service.impl.TuteurServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class DataInit implements InitializingBean {
    private final EnfantServiceImpl enfantService;
    private final TuteurServiceImpl tuteurService;
    public DataInit(EnfantServiceImpl enfantService, TuteurServiceImpl tuteurService) {
        this.enfantService = enfantService;
        this.tuteurService = tuteurService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Tuteur> tutorList = Arrays.asList(
                new Tuteur("rufat","rufat","0485010203","digitalcity",null),
                new Tuteur("Sarah","Sarah","0485010203","digitalcity",null),
                new Tuteur("yassin","yassin","0485010203","digitalcity",null),
                new Tuteur("marcelo","marcelo","0485010203","digitalcity",null)
        );

        List<Enfant> childList = Arrays.asList(
                new Enfant("max","max", LocalDate.of(1992,1,1),true, List.of("arachide","pollene"), Set.of(tutorList.get(3),tutorList.get(1))),
                new Enfant("therence","thenrence", LocalDate.of(1992,1,1),true, null,Set.of(tutorList.get(2))),
                new Enfant("james","james", LocalDate.of(1992,1,1),true, List.of("chat"),Set.of(tutorList.get(0))),
                new Enfant("chidi","chidi", LocalDate.of(1992,1,1),true, null,Set.of(tutorList.get(0)))

        );
        tutorList.forEach(tuteurService::create);
        childList.forEach(enfantService::create);
    }

}
