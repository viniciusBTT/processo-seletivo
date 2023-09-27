package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    SelectiveProgressController selectiveProgressController;

    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("processList", selectiveProgressController.processService.findAll());
        return "index";
    }
}
