package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Modality;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.ModalityRepository;

@Service
public class ModalityService 
{
    @Autowired
    private ModalityRepository modalityRepository;
    
    public Modality findById(Integer id)
    {
        return modalityRepository.findById(id).orElse(null);
    }
}
