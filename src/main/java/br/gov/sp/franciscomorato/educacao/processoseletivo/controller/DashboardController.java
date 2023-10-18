package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gov.sp.franciscomorato.educacao.processoseletivo.service.DashboardService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController
{

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private SelectiveProcessService processService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public String dashboard(Model model)
    {
        model.addAttribute("processQtt", dashboardService.processQtt());
        model.addAttribute("processInProgressQtt", dashboardService.processInProgressQtt());
        model.addAttribute("countAllSubs", dashboardService.countAllSubs());
        model.addAttribute("currentSubscriptions", dashboardService.findCurrentSubscription());
        model.addAttribute("processList", processService.findAll());

        return "dashboard/dashboard";
    }

}
