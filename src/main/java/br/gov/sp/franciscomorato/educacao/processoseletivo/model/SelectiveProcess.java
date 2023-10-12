package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author thiago
 * @see Modality
 */
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
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm", iso = ISO.DATE_TIME)
    @NotNull
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm", iso = ISO.DATE_TIME)
    @NotNull
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm", iso = ISO.DATE_TIME)
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
