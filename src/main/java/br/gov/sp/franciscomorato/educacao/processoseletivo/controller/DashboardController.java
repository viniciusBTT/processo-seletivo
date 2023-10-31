package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.SubscriptionRepository;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SubscriptionService;
import com.google.gson.Gson;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.gov.sp.franciscomorato.educacao.processoseletivo.service.DashboardService;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private SelectiveProcessService processService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public String dashboard(Model model) {
        model.addAttribute("processQtt", dashboardService.processQtt());
        model.addAttribute("processInProgressQtt", dashboardService.processInProgressQtt());
        model.addAttribute("countAllSubs", dashboardService.countAllSubs());
        model.addAttribute("processList", processService.findAll());

        return "dashboard/dashboard";
    }

    @GetMapping(value = "/{processId}")
    public String processDash(@PathVariable Integer processId, Model model) {
        model.addAttribute("processList", processService.findById(processId));
        // model.addAttribute("subscriptions",
        // subscriptionService.findByProcess(processId));
        return "dashboard/process";
    }

    @GetMapping("/subscriptions")
    @ResponseBody
    public ResponseEntity<?> subscriptions(@RequestParam Integer processId, Pageable pageable, @RequestParam int draw, @RequestParam(required = false) String searchValue) {

        HashMap<String, Object> response = new HashMap<>();

        Page<Subscription> page = subscriptionService.findProcessPageable(processId, pageable, searchValue);

        response.put("draw", draw);
        response.put("recordsTotal", page.getTotalElements());
        response.put("data", page.getContent());
        response.put("recordsFiltered", page.getTotalElements());
        return ResponseEntity.ok(response);
    }
}
