package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.SelectiveProcessRepository;

import java.util.Date;
import java.util.List;

@Service
public class SelectiveProcessService
{
    @Autowired
    private SelectiveProcessRepository processRepository;

    /**
     *
     * @param process
     * @return
     */
    public SelectiveProcess save(SelectiveProcess process)
    {
        //salva data atual do servidor
        process.setCreatedAt(new Date());

        //salva quem abriu o processo
        process.setUser(new User(SecurityContextHolder.getContext().getAuthentication().getName()));

        return this.processRepository.save(process);
    }

    /**
     *
     * @param processId
     * @return
     */
    public SelectiveProcess findById(Integer processId)
    {
        return this.processRepository.findById(processId).orElse(null);
    }

    /**
     *
     * @return
     */
    public List<SelectiveProcess> findAll()
    {
        return this.processRepository.findAll();
    }

    /**
     *
     * @return
     */
    public List<SelectiveProcess> findInProgress()
    {
        return this.processRepository.findInProgress();
    }

}
