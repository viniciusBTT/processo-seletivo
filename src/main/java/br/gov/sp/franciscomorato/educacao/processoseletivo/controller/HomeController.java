package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controladora home
 * @author thiago
 */
@Controller
@RequestMapping("/home")
public class HomeController 
{
    
    @GetMapping
    public String home()
    {
        return "home/home";
    }

}
