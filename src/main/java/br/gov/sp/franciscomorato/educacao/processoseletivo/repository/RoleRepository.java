package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thiago
 * @see Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
