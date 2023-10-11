package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SubscriptionService;

/**
 * controladora home
 * @author thiago
 * @see SelectiveProcessService
 * @see CandidateService
 * @see SubscriptionService
 */
@Controller
@RequestMapping("/home")
public class HomeController 
{

    @Autowired
    private SelectiveProcessService processService;

    @Autowired
    private SubscriptionService subscriptionService;
    
    /*** VIEWS */

    /**
     * redireciona para home de acordo com a regra de usuário
     * @param authentication
     * @param model
     * @return
     */
    @GetMapping
    public String home(Authentication authentication, Model model)
    {
        if( authentication.getAuthorities().contains(new Role("ROLE_ADMIN")) ||
        authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ROOT"))) 
        {
            model.addAttribute("username", authentication.getName());
            return "redirect:/process";
        }

        try 
        {
           
            model.addAttribute(
                    "subscriptions", 
                    subscriptionService
                                    .findSubscriptionByCandidateInProgress(
                                            Long.valueOf(authentication.getName())));
            model.addAttribute("processList", processService.findInProgress());
            return "candidate/home";
        } 
        catch (NumberFormatException e)
        {
            model.addAttribute("subscriptions", new ArrayList<>());
            model.addAttribute("processList", processService.findInProgress());
            return "candidate/home";
        }
    }
}
