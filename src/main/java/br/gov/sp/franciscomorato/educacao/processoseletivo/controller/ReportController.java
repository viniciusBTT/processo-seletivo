
package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SubscriptionService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.util.PDFGenerator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author thiago
 * @see SubscriptionService
 * @see PDFGenerator
 */

@Controller
@RequestMapping("/report")
public class ReportController 
{
    
    @Autowired
    private SubscriptionService subscriptionService;
    
    @Autowired
    PDFGenerator pdfGenerator;
    
    /**
     * 
     * @param s -> código de inscrição
     * @return 
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<InputStreamResource> subscriptionReport(@RequestParam Long s, Authentication authentication) 
        throws IOException
    {
        Subscription subscription = subscriptionService.findById(s);
        
        //caso seja inválido
        if(subscription == null) 
        {
            return ResponseEntity.notFound().build();
        }

        /**
         * somente o usuario terá acesso ao seu form de inscrição
         */
        if(!subscription.getCandidate().getUser().getUsername().equals(authentication.getName()))
        {
            return ResponseEntity.notFound().build();
        }
        
        //monta resposta com pdf que carrega na página
        ByteArrayInputStream pdfStream = pdfGenerator.generatePdfReport(subscription);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }
}
