package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Modality;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.ModalityRepository;
import java.util.List;

/**
 * 
 * @author thiago
 * @see ModalityRepository
 * @see Modality
 */
@Service
public class ModalityService 
{
    @Autowired
    private ModalityRepository modalityRepository;
    
    /**
     * 
     * @param id
     * @return 
     */
    public Modality findById(Integer id)
    {
        return modalityRepository.findById(id).orElse(null);
    }
    
    /**
     * 
     * @return 
     */
    public List<Modality> findAll()
    {
        return modalityRepository.findAll();
    }
    
    /**
     * 
     * @param id 
     */
    public void delete(Integer id)
    {
        modalityRepository.deleteById(id);
    }
}
