package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * contém regras de negócio das regras do sistema
 * @author thiago
 * @see Role
 * @see RoleRepository
 */
@Service
public class RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    /**
     * @return lista de roles
     */
    public List<Role> getRoles()
    {
        return roleRepository.findAll();
    }

    /**
     *
     * @param role
     * @return se existir role, senão, nulo
     */
    public Role findRole(String role)
    {
        return roleRepository.findById(role).orElse(null);
    }

    /**
     * salva
     * @param role
     * @return objeto salvo
     */
    public Role save(Role role)
    {
        return roleRepository.save(role);
    }
}
