package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.AddressDTO;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.address.Street;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.AddressService;


@RestController
@RequestMapping("/address")
public class AddressController 
{
    
    @Autowired
    private AddressService addressService;


    @GetMapping
    public ResponseEntity<?> findOrSave(@RequestParam String cep)
    {
        try 
        {
            Street street = addressService.findByCEP(cep);

            if(street != null) 
            {
                return ResponseEntity.ok().body(street);
            }

            AddressDTO addressDTO = addressService.searchCEP(cep);
            
            street = new Street(addressDTO);

            return  ResponseEntity.ok(street);
            
        } 
        catch (Exception e)
        {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
    
    
}
