package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Candidate;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;
import jakarta.validation.Valid;

/**
 * controladora de candidatos
 * @author thiago
 * @see CandidateService
 * @see Candidate
 */
@Controller
@RequestMapping("/candidate")
public class CandidateController
{
    @Autowired
    private CandidateService candidateService;

    /*** METHODS */

    /**
     * método que pesquisa candidato por cpf
     * @param cpf
     * @param auth
     * @return ResponseEntity pronto para requisições JSON
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findCandidate(@RequestParam String cpf, Authentication auth)
    {
        try 
        {
            /**
             * se o cpf pesquisado não for o cpf logado OU
             * se não tiver ROLE_ROOT OU
             * se não tiver ROLE_ADMIN
             * nega acesso
             */
            if(!cpf.equals(auth.getName()) && 
               !auth.getAuthorities().contains(new Role("ROLE_ROOT")) &&
               !auth.getAuthorities().contains(new Role("ROLE_ADMIN"))) 
            {
                return ResponseEntity.notFound().build();
            }

            Candidate candidate = candidateService.findByCpf(cpf);
            
            if(candidate == null) 
            {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(candidate);

        } catch (Exception e) 
        {
            System.out.println("Erro na linha 25 CandidateController: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * @return
     */
    @GetMapping(value="/{id}")
    public String getCandidate(@PathVariable String id, Model model) 
    {
        model.addAttribute("candidate", candidateService.findByCpf(id));
        return "candidate/candidate";
    }

    @PostMapping
    public String postCandidate(@Valid Candidate candidate, Authentication authentication, RedirectAttributes ra)
    {
        try 
        {
            //se for pessoa diferente
            if(!authentication.getName().equals(candidate.getCpf()))
            {
                return "redirect:/error/404";
            }    

            candidateService.save(candidate);

            ra.addAttribute("success", "Dados atualizados com sucesso!");
            return "redirect:/candidate/"+authentication.getName();


        } 
        catch (Exception e) 
        {
            ra.addAttribute("error", "Tente novamente...");
            return "redirect:/candidate/"+authentication.getName();
        }
    }
    
    
}
