package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Candidate;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;

@Controller
@RequestMapping("/candidate")
public class CandidateController
{
    @Autowired
    private CandidateService candidateService;
    

    /**
     * Métodos
     */
    @GetMapping
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
            System.out.println("Erro na linha 25 CandidateController: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
