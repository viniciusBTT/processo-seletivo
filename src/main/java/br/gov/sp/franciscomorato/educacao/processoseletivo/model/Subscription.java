package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

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
    private SelectiveProcess process;

    @ManyToOne
    private Candidate candidate;

    @ManyToMany
    private List<Modality> modalities = new ArrayList<>();

    public Subscription () {}

    public Subscription (SelectiveProcess selectiveProcess) {
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

    
    
}
