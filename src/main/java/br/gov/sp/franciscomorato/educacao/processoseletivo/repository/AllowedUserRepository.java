package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.AllowedUser;

@Repository
public interface AllowedUserRepository extends JpaRepository<AllowedUser, String> {
    
}
