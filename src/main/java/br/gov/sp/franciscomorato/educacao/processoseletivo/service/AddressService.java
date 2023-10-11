package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.gov.sp.franciscomorato.educacao.processoseletivo.ProcessoSeletivoApplication;
import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.AddressDTO;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.address.Street;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.StreetRepository;

/**
 * @author thiago
 * @see https://viacep.com.br/
 * @see StreetRepository
 * @see Street
 * @see ProcessoSeletivoApplication
 * @see AddressDTO
 */
@Service
public class AddressService 
{
    @Autowired
    private StreetRepository streetRepository;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 
     * @param street
     * @return
     */
    public Street save(Street street)
    {
        return streetRepository.save(street);
    }

    /**
     * 
     * @param cep
     * @return
     */
    public Street findByCEP(String cep)
    {
        return streetRepository.findById(cep).orElse(null);
    }


    /*** REST API
     * @param cep
     * @return  */
    public AddressDTO searchCEP(String cep)
    {
        return restTemplate.getForObject("https://viacep.com.br/ws/"+cep+"/json/", AddressDTO.class);
    }

}
