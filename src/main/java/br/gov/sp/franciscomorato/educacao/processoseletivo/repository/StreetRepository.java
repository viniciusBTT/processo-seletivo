package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.address.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, String> 
{
    
}
