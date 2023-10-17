package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;

@Service
public class DashboardService 
{
 
    @Autowired
    private SelectiveProcessService processService;

    @Autowired
    private SubscriptionService subscriptionService;


    /**
     * @return quantidade de processo já criado 
     */
    public Long processQtt()
    {
        return processService.countAll();
    }

    /**
     * 
     * @return quantidade em progresso
     */
    public Long processInProgressQtt()
    {
        return processService.countInProgress();
    }

    /**
     * 
     * @return
     */
    public Long countAllSubs()
    {
        return subscriptionService.countAll();
    }

    public List<Subscription> findCurrentSubscription()
    {
        return subscriptionService.findInProgress();
    }

    
}
