package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.sp.franciscomorato.educacao.processoseletivo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * controladora de autenticação
 * @author thiago
 * @see UserService
 */
@Controller
@RequestMapping({"/auth", "/acesso"})
public class AuthController
{

    @Autowired
    private UserService userService;

    /*** VIEWS */

    /**
     * 
     * @param request
     * @return página de autenticação
     */
    @GetMapping
    public String auth(HttpServletRequest request)
    { 
        return "auth/login";
    }


    /*** METHODS */

    /**
     * verifica autenticação
     * caso a autenticação seja válida, libera acesso
     * encaminhando para home
     * @return
     */
    @GetMapping("/check")
    public String checkAuthentication()
    {
        System.out.println("ernrou");
        boolean isTheAuthenticationValid = userService.isTheAuthenticationValid();

        if(!isTheAuthenticationValid) 
        {
            return "redirect:/auth?error=denied";
        }

        return "redirect:/home";
    }
}
