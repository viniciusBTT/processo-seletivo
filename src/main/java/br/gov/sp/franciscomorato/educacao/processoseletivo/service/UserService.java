package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * regras de negócio de usuário
 * @author thiago
 * @see User
 */
@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.userRepository.findById(username).orElse(null);

        if(user == null) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos.");
        }

        return user;
    }

    /**
     * 
     * @param user
     * @return
     */
    public User save(User user)
    {
        return userRepository.save(user);
    }

    public User createAuthentication(String username, String password)
    {
        User user = new User(username, password);

        return user;
    }

    public User findByUsername(String username)
    {
        return userRepository.findById(username).orElse(null);
    }
}
