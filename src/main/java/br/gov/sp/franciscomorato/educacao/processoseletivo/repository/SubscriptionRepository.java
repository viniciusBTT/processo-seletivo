package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>
{

    @Query(value = "SELECT * FROM subscription WHERE candidate_cpf = :cpf AND process_id = :process", nativeQuery = true)
    Subscription hasSubscription(@Param("cpf") Long cpf, @Param("process") Integer process);
    
}
