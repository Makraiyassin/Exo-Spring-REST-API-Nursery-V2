package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.dto.TokenDTO;
import be.bstorm.akimts.rest.bxl.model.forms.LoginForm;
import be.bstorm.akimts.rest.bxl.model.forms.UtilisateurCreateForm;
import be.bstorm.akimts.rest.bxl.service.impl.CustomUserDetailsServiceImpl;
import be.bstorm.akimts.rest.bxl.utils.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost/","http://localhost:4200/","https://makraiyassin.github.io/"})
@RequestMapping("/user")
public class UtilisateurController {

    private final CustomUserDetailsServiceImpl service;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    public UtilisateurController(CustomUserDetailsServiceImpl service, AuthenticationManager authManager, JwtProvider jwtProvider) {
        this.service = service;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public void createUser(@Valid @RequestBody UtilisateurCreateForm form){
        service.create(form);
    }

    @PostMapping("/login")
    public TokenDTO login(@Valid @RequestBody LoginForm form){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
        return jwtProvider.createToken(auth);
    }

}
