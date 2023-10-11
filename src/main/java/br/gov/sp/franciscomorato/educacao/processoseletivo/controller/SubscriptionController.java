package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Modality;
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
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.EmailService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SubscriptionService;
import jakarta.validation.Valid;
import java.text.SimpleDateFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * controladora de inscrição
 * @author thiago
 * @see SelectiveProcessService
 * @see CandidateService
 * @see SubscriptionService
 * @see EmailService
 * @see SelectiveProcess
 */
@Controller
@RequestMapping({"/subscription", "/inscricao"})
public class SubscriptionController 
{
    
    @Autowired
    private SelectiveProcessService processService;
    
    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private SubscriptionService subscriptionService;
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/{processId}")
    public String subscriptionView(@PathVariable Integer processId, Model model, Authentication auth)
    {

        SelectiveProcess process = processService.findById(processId);

        if(process != null) 
        {
            var cpf = Long.valueOf(auth.getName());
            
            boolean hasSubscription = subscriptionService.hasSubscription(cpf, processId);
            
            if(hasSubscription) 
            {
                model.addAttribute("hasSubscription", hasSubscription);
                model.addAttribute( "subscription", subscriptionService.findSubscription(cpf, processId));    
            }
            else
            {
                model.addAttribute(
                        "subscription", 
                        new Subscription(
                                process,
                                candidateService.findByCpf(Long.valueOf(auth.getName()))));
            }
            
        } 
        else 
        {
            return "error/404";
        }

        return "subscription/subscription";
    }
    
    
    @PostMapping
    public String postSubscription(@Valid Subscription subscription, 
            RedirectAttributes ra,
            @RequestParam(required = false) Integer[] modalities) 
    {
        try 
        {

            if(modalities == null)
            {
                ra.addFlashAttribute("error", "Selecione uma ou mais modalidades.");
                return "redirect:/inscricao/" + subscription.getProcess().getId();
            }

            subscription = subscriptionService.save(subscription, modalities);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            if(subscription == null) {
                ra.addFlashAttribute("error", "Processo Seletivo inválido.");
            }
            else 
            {
                //adiciona candidato
                subscription.setCandidate(candidateService.findByCpf(subscription.getCandidate().getCpf()));
                subscription.setProcess(processService.findById(subscription.getProcess().getId()));

                //envia inscrição por e-mail
                System.out.println("Enviando e-mail....");

                ra.addFlashAttribute("success", "A sua inscrição foi realizada com sucesso!");
                
                String text = "Prezado(a) " + subscription.getCandidate().getName() + ",\n\n"
                + "Confirmamos a sua inscrição no "+ subscription.getProcess().getTitle() +".\n\n"
                + "Aqui estão os detalhes importantes sobre a sua inscrição:\n\n"
                + "Número da Inscrição: " + subscription.getId() + "\n"
                + "Nome do Candidato: " + subscription.getCandidate().getName() + "\n"
                + "Data da Inscrição: " + sdf.format(subscription.getCreateAt())  +"\n\n"
                + "Modalidades: " + subscription.modalitiesToString() + "\n\n\n"
                + "Atenciosamente,\n\n"
                + "Diretoria de Tecnologia Interna\n"
                + "Secretaria Municipal de Educação\n"
                + "Prefeitura de Francisco Morato\n"
                + "(11) 4489-8900";

                emailService.sendmail(
                    subscription.getCandidate().getEmail(),
                    "Confirmação de Inscrição no Processo Seletivo",
                    text
                );
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
