package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
}
