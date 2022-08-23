package be.bstorm.akimts.rest.bxl.utils;

import be.bstorm.akimts.rest.bxl.model.forms.AdresseForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;
import be.bstorm.akimts.rest.bxl.model.forms.UtilisateurCreateForm;
import be.bstorm.akimts.rest.bxl.service.impl.CustomUserDetailsServiceImpl;
import be.bstorm.akimts.rest.bxl.service.impl.EnfantServiceImpl;
import be.bstorm.akimts.rest.bxl.service.impl.TuteurServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class DataInit implements InitializingBean {
    private final EnfantServiceImpl enfantService;
    private final TuteurServiceImpl tuteurService;
    private final CustomUserDetailsServiceImpl userDetailsService;
    public DataInit(EnfantServiceImpl enfantService, TuteurServiceImpl tuteurService, CustomUserDetailsServiceImpl userDetailsService) {
        this.enfantService = enfantService;
        this.tuteurService = tuteurService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<TuteurForm> tutorList = Arrays.asList(
                new TuteurForm("rufat","rufat",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("Sarah","Sarah",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("yassin","yassin",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("yassin","yassin",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("yassin","yassin",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("yassin","yassin",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("yassin","yassin",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("yassin","yassin",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("yassin","yassin",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203"),
                new TuteurForm("marcelo","marcelo",new AdresseForm(1,'1',"rue digitalCity","bxl",1000),"0485010203")
        );

        List<EnfantInsertForm> childList = Arrays.asList(
                new EnfantInsertForm("max","max", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("max","max", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("max","max", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("max","max", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("max","max", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("max","max", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("max","max", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("therence","thenrence", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("james","james", LocalDate.of(1992,1,1)),
                new EnfantInsertForm("chidi","chidi", LocalDate.of(1992,1,1))

        );
        tutorList.forEach(tuteurService::create);
        childList.forEach(enfantService::create);
        userDetailsService.create(new UtilisateurCreateForm("a","a"));
    }

}
