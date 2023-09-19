package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController
{
    @GetMapping
    @ResponseBody
    public String index()
    {
        return "<h2>Olá mundo</h2>";
    }
}
