package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controladora de autenticação
 * @author thiago
 */
@Controller
@RequestMapping({"/auth", "/acesso"})
public class AuthController
{

    @GetMapping
    public String auth()
    {
        return "auth/login";
    }

}
