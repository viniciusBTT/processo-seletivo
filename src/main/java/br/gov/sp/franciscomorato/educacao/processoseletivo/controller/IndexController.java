package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;

/**
 * controladora de início
 * @author thiago
 */
@Controller
@RequestMapping("/")
public class IndexController
{

    @Autowired
    SelectiveProcessService processService;

    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("processList", processService.findInProgress());
        return "index";
    }
}
