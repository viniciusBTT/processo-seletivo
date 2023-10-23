package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import java.util.List;

/**
 * @author thiago
 * @see Subscription
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>
{
    @Query(value = "SELECT * FROM subscription WHERE candidate_cpf = :cpf AND process_id = :process", nativeQuery = true)
    Subscription hasSubscription(@Param("cpf") String cpf, @Param("process") Integer process);
    
    @Query(value = "SELECT s FROM Subscription s WHERE s.candidate.cpf = :cpf")
    List<Subscription> findSubscriptionByCandidateInProgress(@Param("cpf") String cpf);

    @Query(value = "SELECT s FROM Subscription s WHERE now() BETWEEN s.process.startDate AND s.process.endDate")
    List<Subscription> findSubscriptionInProgress();

    @Query(value = "SELECT s FROM Subscription  s WHERE s.process.id = :processId")
    List<Subscription> findSubscriptionByProcessId(@Param("processId") Integer processId) ;

    @Query(value = "SELECT s FROM Subscription  s WHERE s.process.id = :processId")
    Page<Subscription> findSubscriptionByProcessPageable(@Param("processId") Integer processId, Pageable pageable);

}
