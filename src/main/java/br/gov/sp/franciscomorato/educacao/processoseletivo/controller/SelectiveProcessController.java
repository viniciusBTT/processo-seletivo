package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.ModalityDTO;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Modality;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * processo seletivo
 * @author thiago
 * @see SelectiveProcess
 * @see SelectiveProcessService
 */
@Controller
@RequestMapping({"/process", "/processoseletivo"})
public class SelectiveProcessController
{
    @Autowired
    protected SelectiveProcessService processService;

    /*** VIEWS */
    
    /**
     * 
     * @param model
     * @return 
     */
    @GetMapping
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public String selectiveProcess(Model model)
    {
        
        model.addAttribute("processList", processService.findAll());
        return "process/list";
    }

    /**
     * 
     * @param model
     * @param id
     * @param modalityDTO
     * @return 
     */
    @GetMapping("/{id}")
    public String process(Model model, @PathVariable Integer id, ModalityDTO modalityDTO)
    {
        model.addAttribute("selectiveProcess", processService.findById(id));
        return "process/process";
    }

    /**
     * 
     * @param selectiveProcess
     * @return 
     */
    @GetMapping("/add")
    public String add(SelectiveProcess selectiveProcess)
    {
        return "process/process";
    }

    /******
     * API
     ******/

    /**
     * salva processo
     * @param selectiveProcess
     * @param ra
     * @return
     */
    @PostMapping
    public String persist(SelectiveProcess selectiveProcess, RedirectAttributes ra)
    {
        try
        {
            selectiveProcess = processService.save(selectiveProcess);

            if(selectiveProcess != null)
            {
                ra.addFlashAttribute("success", "Processo Seletivo criado com sucesso");
            }

            return "redirect:/process/" + (selectiveProcess.getId() != null ? selectiveProcess.getId() : "error");
        }
        catch (Exception e)
        {
            ra.addFlashAttribute("error", "O processo não foi salvo. Por favor, entre em contato com o administrador do sistema.");
            System.out.println("Erro ao salvar processo seletivo na linha 58: " + e.getMessage());
            ra.addAttribute("error", "O processo não foi salvo. Verifique com o administrador do sistema.");
        }

        return "process/process";

    }

    /**
     * salva modalidade
     * @param modalityDTO
     * @return
     */
    @PostMapping("/modality")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> addModality(@RequestBody ModalityDTO modalityDTO)
    {
        try
        {
            //verifica atributos nulos
            if(modalityDTO.processId() == null || modalityDTO.name() == null)
            {
                return ResponseEntity.ofNullable("Objeto fornecido está incompleto.");
            }

            //procura processo
            SelectiveProcess process = processService.findById(modalityDTO.processId());

            if(process == null)
            {
                return ResponseEntity.ofNullable("Processo Seletivo inválido.");
            }

            Modality modality = new Modality(modalityDTO.name());

            //adiciona modalidade
            process.addModality(modality);

            return ResponseEntity.ok(process);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body("Erro ao salvar modalidade");
        }
    }

}
