package br.gov.sp.franciscomorato.educacao.processoseletivo.service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.SelectiveProcessRepository;

import java.util.Date;
import java.util.List;

/**
 * @author thiago
 * @see SelectiveProcessRepository
 * @see SelectiveProcess
 */
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
        if(process.getId() != null) {

            SelectiveProcess oldProcess = processRepository.findById(process.getId()).orElse(null);

            if(oldProcess != null)
                process.setModalities(oldProcess.getModalities());
        }

        //salva data atual do servidor
        process.setCreatedAt(new Date());

        process.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

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

    /**
     * 
     * @return
     */
    public Long countAll()
    {
        return this.processRepository.count();
    }

    public Long countInProgress()
    {
        return this.processRepository.countInProgress();
    }

}
