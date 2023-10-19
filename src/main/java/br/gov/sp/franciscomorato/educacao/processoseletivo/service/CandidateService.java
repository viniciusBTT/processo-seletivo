package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Candidate;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.CandidateRepository;

/**
 * @author thiago
 * @see CandidateRepository
 * @see UserService
 * @see Candidate
 */
@Service
public class CandidateService 
{

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private UserService userService;

    /**
     * 
     * @param candidate
     * @return
     */
    public Candidate save (Candidate candidate)
    {

        Candidate hasCandidate = candidateRepository.findById(candidate.getCpf()).orElse(null);

        if(hasCandidate != null)
        {
            candidate.setUser(hasCandidate.getUser());
            return candidateRepository.save(candidate);
        }

        //verificar se há usuário
        if(candidate.getUser() != null)
        {
            candidate.getUser().setUsername(String.valueOf(candidate.getCpf()));

            User user = userService.findByUsername(candidate.getUser().getUsername());

            if(user != null) 
            {
                return null;
            }

            user = candidate.getUser();

            User newUser = new User(user.getUsername(),user.getPassword(),new Role("ROLE_COMUM"));

            candidate.setUser(newUser);
        }
        else
        {
            return null;
        }

        return candidateRepository.save(candidate);
        
    }

    /**
     * 
     * @param cpf
     * @return
     */
    public Candidate findByCpf(String cpf)
    {
        return candidateRepository.findById(cpf).orElse(null);
    }
    
}
