package br.gov.sp.franciscomorato.educacao.processoseletivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author thiago
 * @see Role
 */
@Entity
@Data
public class User implements UserDetails
{
    @Id
    private String username;

    @JsonIgnore
    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    public User () {}

    public User (String username)
    {
        this.username = username;
    }

    //cria usuario
    public User (String username, String password, Role role) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.roles.add(role);
    }

    public String encrypt(String password) 
    {
        this.password = new BCryptPasswordEncoder().encode(password);
        return this.password;
    }

    public boolean addRole(Role role)
    {
        return this.roles.add(role);   
    }

    public boolean addRoles(List<Role> roles)
    {
        return this.roles.addAll(roles);
    }

    public boolean removeRole(Role role)
    {
        return this.roles.remove(role);
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
