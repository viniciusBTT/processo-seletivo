package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;

/**
 * controladora de inscrição
 * @author thiago
 * @see SelectiveProcessService
 */
@Controller
@RequestMapping("/subscription")
public class SubscriptionController 
{
    
    @Autowired
    private SelectiveProcessService processService;

    @GetMapping("/{processId}")
    public String subscriptionView(@PathVariable Integer processId, Model model)
    {

        SelectiveProcess process = processService.findById(processId);

        if(process != null)
            model.addAttribute("subscription", new Subscription(process));
        else
            return "error/404";

        return "subscription/subscription";
    }
    
}
