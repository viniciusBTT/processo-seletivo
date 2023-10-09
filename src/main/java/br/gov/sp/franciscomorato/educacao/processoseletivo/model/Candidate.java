package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.address.Street;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

import lombok.Data;

@Entity
@Data
public class Candidate
{
    @Id
    private Long cpf;
    
    @NotNull
    private String name;

    @NotNull
    private String rg;

    @NotNull
    private String birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phone;

    private String diseaseInducedDeficiency;

    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Street street;

    private String addressNumber;
   
}
