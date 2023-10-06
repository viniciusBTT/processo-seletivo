package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SelectiveProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;
    
    private String imageUrl;

    @Lob
    @Column(name = "description", columnDefinition = "LONGTEXT")
    @NotNull
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/mm/yyyy HH:mm")
    @NotNull
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/mm/yyyy HH:mm")
    @NotNull
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/mm/yyyy HH:mm")
    private Date createdAt;
    
    @OneToMany(cascade = CascadeType.ALL)
    @NotNull
    private List<Modality> modalities = new ArrayList<>();

    private String createdBy;


    public boolean addModality(Modality modality)
    {
        return this.modalities.add(modality);
    }

    public boolean removeModality(Modality modality)
    {
        return this.modalities.remove(modality);
    }

    
}
