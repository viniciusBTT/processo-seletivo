
package br.gov.sp.franciscomorato.educacao.processoseletivo.model.address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author thiago
 */
@Entity
@Data
public class City 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    private State state;

    public City () {}

    public City(String name, String uf) {
        this.name = name;
        this.state = new State(uf);
    }
}
