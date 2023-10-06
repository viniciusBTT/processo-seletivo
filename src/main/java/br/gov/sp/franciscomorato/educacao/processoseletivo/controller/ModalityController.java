
package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.ModalityDTO;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Modality;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.ModalityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thiago
 */
@RestController
@RequestMapping("/modality")
public class ModalityController 
{
    
    @Autowired
    private ModalityService modalityService;
    
    /**
     * 
     * @return 
     */
    @GetMapping
    public ResponseEntity<List<Modality>> getModalities()
    {
        List<Modality> modalities = modalityService.findAll();
        
        if(modalities.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(modalities);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Modality> putModality(@PathVariable Integer id, @RequestBody ModalityDTO modality)
    {
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        try 
        {
            Modality modality = modalityService.findById(id);
            
            if(modality == null)
            {
                return ResponseEntity.notFound().build();
            }
            
            modalityService.delete(id);
            
            return ResponseEntity.ok("Modalidade removida.");
            
        } 
        catch (Exception e) 
        {
            return ResponseEntity.internalServerError().body("Erro ao remover modalidade");
        }
    }
}
