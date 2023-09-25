package br.gov.sp.franciscomorato.educacao.processoseletivo.controller;

import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.ModalityDTO;
import br.gov.sp.franciscomorato.educacao.processoseletivo.dto.ModalityResponseDTO;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Modality;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.SelectiveProcess;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.SelectiveProcessService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
public class SelectiveProgressController
{
    @Autowired
    private SelectiveProcessService processService;

    /******
     * ONLY VIEWS
     * ****/
    @GetMapping
    public String selectiveProcess(Model model)
    {
        model.addAttribute("processList", processService.findAll());
        return "process/list";
    }

    @GetMapping("/{id}")
    public String process(Model model, @PathVariable Integer id)
    {
        model.addAttribute("process", processService.findById(id));
        return "process/process";
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        model.addAttribute("process", new SelectiveProcess());
        return "process/process";
    }

    /******
     * API
     ******/

    /**
     * salva processo
     * @param process objeto de SelectiveProcess preenchido
     * @param ra
     * @return
     */
    @PostMapping
    public String persist(@ModelAttribute SelectiveProcess process, RedirectAttributes ra)
    {
        try
        {
            process = processService.save(process);

            if(process != null)
            {
                ra.addFlashAttribute("success", "Processo Seletivo criado com sucesso");
            }

            return "redirect:/processo/" + (process.getId() != null ? process.getId() : "error");
        }
        catch (Exception e)
        {
            ra.addFlashAttribute("error", "O processo não foi salvo. Por favor, entre em contato com o administrador do sistema.");
            System.out.println("Erro ao salvar processo seletivo na linha 58: " + e.getMessage());
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
            if(modalityDTO.processId().equals(null) || modalityDTO.name().equals(null))
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
