package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author thiago
 */
@Entity
@Getter
@Setter
public class Modality 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    public Modality()
    {

    }

    public Modality(String name)
    {
        this.name = name;
    }
}
