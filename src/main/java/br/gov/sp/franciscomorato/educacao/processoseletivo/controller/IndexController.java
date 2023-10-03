package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.sp.franciscomorato.educacao.processoseletivo.service.EmailService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;

/**
 * controladora de início
 * @author thiago
 * @see SelectiveProcessService
 */
@Controller
@RequestMapping("/")
public class IndexController
{

    @Autowired
    SelectiveProcessService processService;

    /*** VIEWS */

    /**
     * retorna index público
     * @param model
     * @param authentication
     * @return
     */
    @GetMapping
    public String index(Model model, Authentication authentication)
    {
        model.addAttribute("processList", processService.findInProgress());

        if(authentication == null) {
            return "index";
        }
        
        return "redirect:/home";
    }
}
