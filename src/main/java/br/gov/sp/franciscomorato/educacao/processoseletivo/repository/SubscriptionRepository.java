package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>
{

    @Query(value = "SELECT * FROM subscription WHERE candidate_cpf = :cpf AND process_id = :process", nativeQuery = true)
    Subscription hasSubscription(@Param("cpf") Long cpf, @Param("process") Integer process);
    
    @Query(value = "select * from subscription s inner join selective_process sp on sp.id = s.process_id where s.candidate_cpf = :cpf and now() < sp.end_date", nativeQuery = true)
    List<Subscription> findSubscriptionByCandidateInProgress(@Param("cpf") Long cpf);
    
}
