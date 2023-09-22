package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.SelectiveProcessRepository;

@Service
public class SelectiveProcess 
{
    @Autowired
    private SelectiveProcessRepository processRepository;
    
    public SelectiveProcess save(SelectiveProcess process)
    {
        return this.processRepository.save(process);
    }



}
