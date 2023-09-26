package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
public class Role implements GrantedAuthority
{
    @Id
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public Role()
    {
        
    }

    public Role(String role)
    {
        this.name = role;
    }
}
