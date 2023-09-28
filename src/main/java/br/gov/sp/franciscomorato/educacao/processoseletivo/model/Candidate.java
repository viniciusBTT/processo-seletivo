package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Candidate
{
    @Id
    private Long cpf;
    
    private String name;

    private String rg;

    private String birthDate;

    private String email;

    private String phone;

    private String diseaseInducedDeficiency;

    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;



    

}
