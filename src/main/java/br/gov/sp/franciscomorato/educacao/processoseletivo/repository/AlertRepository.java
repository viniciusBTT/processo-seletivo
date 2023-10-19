package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>
{
    @Query(value = "SELECT * FROM alert ORDER BY id desc limit 1", nativeQuery = true)
    Alert findLastAlert();
}
