package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.UserService;

/**
 * controladora home
 * @author thiago
 */
@Controller
@RequestMapping("/home")
public class HomeController 
{

    @Autowired
    private UserService userService;
    
    @GetMapping
    public String home(Authentication authentication)
    {

        if(authentication.getAuthorities().contains(new Role("ROLE_ADMIN")))
        {
            return "home/home";
        }

        return "index";
    }

}
