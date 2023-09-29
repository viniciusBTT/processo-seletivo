package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Modality;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.SubscriptionRepository;

/**
 * regras de inscriçao
 * @author thiago
 * @see Subscription
 * @see SubscriptionRepository
 * @see SelectiveProcessService
 * @see ModalityService
 */
@Service
public class SubscriptionService 
{
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SelectiveProcessService selectiveProcessService;

    @Autowired
    private ModalityService modalityService;

    /**
     * persiste modalidade
     * @param subscription objeto de inscrição
     * @param modalities lista de modalityId
     * @return null se não for possível sava
     */
    public Subscription save(Subscription subscription, Integer[] modalities)
    {

        SelectiveProcess process = selectiveProcessService.findById(subscription.getProcess().getId());

        //se não tem processo
        if(process == null) 
        {
            return null;
        }

        //se possui inscrição
        if(hasSubscription(subscription.getCandidate().getCpf(), process.getId())) return null;
        
        subscription.setCreateAt(new Date());

        List<Modality> selectedModalities = new ArrayList<>();

        //adiciona modalidades
        for (Integer modalityId : modalities) {
            //tratar se id nãoo for válido
            selectedModalities.add(modalityService.findById(modalityId));
        }

        subscription.setModalities(selectedModalities);

        return subscriptionRepository.save(subscription);
    }

    /**
     * verifica se existe inscrição
     * @param cpf
     * @param processId
     * @return
     */
    public boolean hasSubscription(Long cpf, Integer processId) 
    {
        //caso nao tenha autenticacao
        return subscriptionRepository.hasSubscription(cpf, processId) != null;
    }
    
    /**
     * encontra inscrições em andamento
     * @param cpf
     * @return 
     */
    public List<Subscription> findSubscriptionByCandidateInProgress(Long cpf)
    {
        return subscriptionRepository.findSubscriptionByCandidateInProgress(cpf);
    }
    
}
