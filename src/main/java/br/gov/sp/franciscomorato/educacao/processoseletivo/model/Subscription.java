package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author thiago
 * @see SelectiveProcess
 * @see Candidate
 * @see Modality
 */
@Entity
@Data
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @ManyToOne
    @NotNull
    private SelectiveProcess process;

    @ManyToOne
    @NotNull
    private Candidate candidate;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    private List<Modality> modalities = new ArrayList<>();

    public Subscription () {}

    public Subscription (SelectiveProcess selectiveProcess, Candidate candidate) 
    {
        this.candidate = candidate;
        this.process = selectiveProcess;
    }

    public boolean addModality(Modality modality) 
    {
        return this.modalities.add(modality);
    }

    public boolean addModalities(List<Modality> modalities)
    {
        return this.modalities.addAll(modalities);
    }

    public String modalitiesToString()
    {
        String textReturn = "";
        
        int i = 0;
        
        while(i < this.modalities.size())
        {
            textReturn += modalities.get(i).getName();
            i++;
            
            if(i < this.modalities.size())
                textReturn += " |";
        }
        
        return textReturn;
    }
    
}
