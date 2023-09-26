package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controladora de início
 * @author thiago
 */
@Controller
@RequestMapping("/")
public class IndexController
{
    @GetMapping
    public String index()
    {
        return "index";
    }
}
