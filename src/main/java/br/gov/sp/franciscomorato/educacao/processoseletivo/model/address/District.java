
package br.gov.sp.franciscomorato.educacao.processoseletivo.model.address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @author thiago
 * @see City
 */
@Entity
@Data
public class District 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private City city;

    public District () {

    }

    public District (String name, String city, String uf) {
        this.name = name;
        this.city = new City(city, uf);
    }
}
