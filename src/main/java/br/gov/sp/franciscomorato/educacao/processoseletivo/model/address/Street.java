
package br.gov.sp.franciscomorato.educacao.processoseletivo.model.address;

import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.AddressDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author thiago
 * @see District
 */
@Entity
@Data
public class Street 
{
    @Id
    private String cep;
    
    private String logradouro;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private District district;

    public Street () {}

    public Street (AddressDTO addressDTO)
    {
        this.cep = addressDTO.cep();
        this.logradouro = addressDTO.logradouro();
        this.district = new District(addressDTO.bairro(), addressDTO.localidade(), addressDTO.uf());
    }
}
