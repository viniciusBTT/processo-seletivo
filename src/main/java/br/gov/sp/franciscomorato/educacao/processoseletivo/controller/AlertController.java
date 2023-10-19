package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.layout.element.List;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Alert;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.AlertRepository;

@RestController
@RequestMapping("/alert")
public class AlertController
{
    @Autowired
    private AlertRepository alertRepository;
    
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ResponseEntity<Alert> add(@RequestParam String message)
    {
        Alert alert = new Alert();
        alert.setMessage(message);
        alert = alertRepository.save(alert);
        return ResponseEntity.ok(alert);
    }

}
