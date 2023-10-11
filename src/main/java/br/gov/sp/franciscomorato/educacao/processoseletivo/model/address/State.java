
package br.gov.sp.franciscomorato.educacao.processoseletivo.model.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author thiago
 */
@Entity
@Data
public class State 
{
    @Id
    private String uf;
    
    private String name;

    public State () {}

    public State (String uf) {
        this.uf = uf;
    }
}
