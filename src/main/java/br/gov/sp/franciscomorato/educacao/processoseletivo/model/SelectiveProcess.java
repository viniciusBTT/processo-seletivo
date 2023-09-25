package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class SelectiveProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private Date createdAt;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Modality> modalities = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    public boolean addModality(Modality modality)
    {
        return this.modalities.add(modality);
    }

    public boolean removeModality(Modality modality)
    {
        return this.removeModality(modality);
    }

    
}
