package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;

import java.util.List;

/**
 * @author thiago
 * @see SelectiveProcess
 */
@Repository
public interface SelectiveProcessRepository extends JpaRepository<SelectiveProcess, Integer>
{
    @Query(value = "SELECT * FROM selective_process where end_date < now()", nativeQuery = true)
    List<SelectiveProcess> findInProgress();
}
