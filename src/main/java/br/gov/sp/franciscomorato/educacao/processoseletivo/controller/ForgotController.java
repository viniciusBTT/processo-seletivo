
package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.ForgotDTO;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Candidate;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.CandidateService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.EmailService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author thiago
 */
@Controller
@RequestMapping("/forgot")
public class ForgotController 
{
    
    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping
    public String forgotPage()
    {
        return "forgot/forgot";
    }
    
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(HttpServletRequest request, 
            @RequestBody ForgotDTO forgotDTO)
    {
        try 
        {
            Candidate candidate = candidateService.findByCpf(forgotDTO.candidate());
            
            if(candidate == null) return ResponseEntity.badRequest().build();
            
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(candidate.getUser(), token);
            emailService.sendmail(candidate.getEmail(), "Redefinição de senha", "https://processoseletivo.franciscomorato.sp.gov.br/forgot/validate/" + token);
            return ResponseEntity.ok("Um link de redefinição foi enviado ao seu e-mail.");
        } 
        catch (Exception e) 
        {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/validate/{token}")
    public String validateToken(@PathVariable String token, Model model)
    {
        try 
        {
            var passwordResetToken = userService.validatePasswordResetToken(token);
            
            if(passwordResetToken == null)
            {
                return "redirect:/auth?message='expired'";
            }
            
            model.addAttribute("user", passwordResetToken.getUser());
            return "auth/update";
            
        } catch (Exception e) 
        {
            return "error";
        }
    }
    
    @PostMapping("/changePassword")
    @Transactional
    public String change(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String token,
            RedirectAttributes ra)
    {
        try 
        {
            var passwordResetToken = userService.validatePasswordResetToken(token);
            
            if(passwordResetToken == null)
            {
                return "redirect:/auth?message='expired'";
            }
            
            User user = userService.findByUsername(username);
            
            user.setPassword(user.encrypt(password));
            
            userService.save(user);
            
            userService.deleteToken(token);
            
            ra.addFlashAttribute("success", "Senha alterada com sucesso!");
            return "redirect:/auth";
            
        } 
        catch (Exception e) 
        {
            System.out.println("Erro ao alterar senha: " + e.getMessage());
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth";
        }
    }
    
    /**
     * encontra por cpf e esconde o e-mail
     * @param cpf
     * @return 
     */
    @GetMapping("/find")
    @ResponseBody
    public ResponseEntity<ForgotDTO> find(@RequestParam String cpf)
    {
        try 
        {
            Candidate candidate = candidateService.findByCpf(cpf);
            
            if(candidate == null) {
                return ResponseEntity.notFound().build();
            }
            
            StringBuilder email = new StringBuilder(candidate.getEmail());
            
            
            for(int i = 0; i < email.length(); i++)
            {
                if(i > 2 && i < email.lastIndexOf("@")) {
                    
                    email.setCharAt(i, '*');
                    
                }
                
                if(i > email.lastIndexOf("@") && i < email.length() - 4)
                {
                    email.setCharAt(i, '*');
                }
            }
            
            ForgotDTO response;
            response = new ForgotDTO(candidate.getCpf(), email.toString());
            
            return ResponseEntity.ok(response);
        } 
        catch (Exception e) 
        {
            return ResponseEntity.badRequest().build();
        }
        
    }
    
}
