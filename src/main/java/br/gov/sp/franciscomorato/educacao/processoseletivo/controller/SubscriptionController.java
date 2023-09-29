package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SubscriptionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/{processId}")
    public String subscriptionView(@PathVariable Integer processId, Model model, Authentication auth)
    {

        SelectiveProcess process = processService.findById(processId);

        if(process != null) {
            model.addAttribute(
                    "subscription", 
                    new Subscription(
                            process,
                            candidateService.findByCpf(Long.valueOf(auth.getName()))));
        } else {
            return "error/404";
        }

        return "subscription/subscription";
    }
    
    
    @PostMapping
    public String postSubscription(Subscription subscription, 
            RedirectAttributes ra,
            @RequestParam Integer[] modalities) 
    {
        try 
        {
            subscription = subscriptionService.save(subscription, modalities);
            
            if(subscription == null) {
                ra.addFlashAttribute("error", "Esse processo inválido.");
            }
            else {
                ra.addAttribute("success", "A sua inscrição foi realizada com sucesso!");
            }
        } 
        catch (Exception e)
        {
            System.out.println("Erro ao realizar inscrição: " + e.getMessage());
                ra.addFlashAttribute("error", "Esse processo inválido.");
        }
        return "redirect:/home";
    }
    
}
