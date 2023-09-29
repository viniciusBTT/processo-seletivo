package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Candidate;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;

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
    
    /*** VIEWS */

    /*** METHODS */

    /**
     * método que pesquisa candidato por cpf
     * @param cpf
     * @return ResponseEntity pronto para requisições JSON
     */
    @GetMapping
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
            System.out.println("Erro na linha 25 CandidateController: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
