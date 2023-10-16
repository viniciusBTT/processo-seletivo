
package br.gov.sp.franciscomorato.educacao.processoseletivo.repository;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thiago
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>
{
    PasswordResetToken findByToken(String token);
    
    void deleteByToken(String token);
}
