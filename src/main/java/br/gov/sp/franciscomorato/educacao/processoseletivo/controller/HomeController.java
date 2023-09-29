package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * controladora home
 * @author thiago
 */
@Controller
@RequestMapping("/home")
public class HomeController 
{

    @Autowired
    private SelectiveProcessService processService;

    @Autowired
    private CandidateService candidateService;
    
    @GetMapping
    public String home(Authentication authentication, Model model)
    {
        if( authentication.getAuthorities().contains(new Role("ROLE_ADMIN")) ||
        authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ROOT"))
        ) {
            model.addAttribute("username", authentication.getName());
            return "redirect:/process";
        }

        model.addAttribute("processList", processService.findInProgress());
        model.addAttribute("username", candidateService.findByCpf(Long.parseLong(authentication.getName())).getName());
        
        return "candidate/home";
    }

}
