package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.AllowedUser;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.PasswordResetToken;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.AllowedUserRepository;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.PasswordResetTokenRepository;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * regras de negócio de usuário
 * @author thiago
 * @see User
 * @see UserRepository
 * @see AllowedUserRepository
 * @see AllowedUser
 */
@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AllowedUserRepository allowedUserRepository;
    
    @Autowired
    private PasswordResetTokenRepository resetRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.userRepository.findById(username).orElse(null);
        
        if(user != null)
        {
            if(user.getAuthorities().isEmpty())
            {
                user.addRole(new Role("ROLE_COMUM"));
            }
        }

        if(user == null) throw new UsernameNotFoundException("Usuário ou senha inválidos");
        
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



    /**
     * encontra pelo numero de usuário
     * @param username
     * @return
     */
    public User findByUsername(String username)
    {
        return userRepository.findById(username).orElse(null);
    }

    public boolean isTheAuthenticationValid()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authenticated = findByUsername(auth.getName());

        if(authenticated == null) 
        {
            AllowedUser allowed = findAllowedUser(auth.getName());

            if(allowed == null) 
            {
                SecurityContextHolder.getContext().setAuthentication(null);
                return false;
            }

            List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();

            updatedAuthorities.add(new SimpleGrantedAuthority(allowed.getRoles().get(0).getName()));
            
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                            SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                            updatedAuthorities)
            );
            
            return true;
        }

        return true;
    }


    public AllowedUser findAllowedUser(String username)
    {
        return this.allowedUserRepository.findById(username).orElse(null);
    }
    
    public void createPasswordResetTokenForUser(User user, String token)
    {
        PasswordResetToken prt = new PasswordResetToken(token, user);
        resetRepository.save(prt);
    }
    
    public PasswordResetToken validatePasswordResetToken(String token)
    {
        final PasswordResetToken passwordResetToken = resetRepository.findByToken(token);
        
        return !isTokenFound(passwordResetToken) ? null
                : isTokenExpired(passwordResetToken) ? null
                : passwordResetToken;
        
    }
    
    public void deleteToken(String token)
    {
        resetRepository.deleteByToken(token);
    }
    
    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
    


}
