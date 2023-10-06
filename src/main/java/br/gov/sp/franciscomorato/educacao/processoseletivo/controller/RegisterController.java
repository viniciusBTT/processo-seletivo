package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Candidate;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;
import jakarta.validation.Valid;

/**
 * controladora de cadastro de candidato
 * @author thiago
 */
@Controller
@RequestMapping("/register")
public class RegisterController
{

    @Autowired
    private CandidateService candidateService;
    
    /*** VIEWS */
    /**
     * 
     * @param candidate
     * @return
     */
    @GetMapping
    public String register(Candidate candidate)
    {
        return "auth/register";
    }

    /** VIEWS */

    /**
     * 
     * @param candidate
     * @param ra
     * @return
     */
    @PostMapping
    public String save(@Valid Candidate candidate, RedirectAttributes ra)
    {
        try 
        {
            candidate = candidateService.save(candidate);    

            if(candidate == null)
            {
                ra.addFlashAttribute("error", "Não foi possível salvar o candidato.");
                return register(candidate);
            }
            
            

            ra.addFlashAttribute("success", "Sua candidatura foi salva com sucesso!");
            ra.addFlashAttribute("message", "Acesse o sistema para verificar sua inscrição.");
            return "redirect:/acesso";
        }
        catch (Exception e) 
        {
            System.out.println("Erro na linha 44: " + e.getMessage());    
            return register(candidate);
        }
    }

    /**
     * 
     * @param cpf
     * @return 
     */
    @GetMapping("/candidate")
    @ResponseBody
    public ResponseEntity<?> findCandidate(@RequestParam Long cpf)
    {
        try 
        {
            Candidate candidate = candidateService.findByCpf(cpf);            
            if(candidate == null) 
            {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(candidate);

        } catch (Exception e) 
        {
            System.out.println("Erro na linha 74 CandidateController: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
