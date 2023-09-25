package br.gov.sp.franciscomorato.educacao.processoseletivo.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

/**
 * author thiago
 */
@Entity
@Data
public class AllowedUser 
{
    @Id
    private String username;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public boolean addRole(Role role) 
    {
        return this.roles.add(role);
    }

    public boolean removeRole(Role role)
    {
        return this.roles.remove(role);
    }
}
