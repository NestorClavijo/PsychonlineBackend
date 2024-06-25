package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Medico;
import psychOnline.psychonline.service.MedicoService;

import java.util.Optional;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping("/new")
    public Medico newMedico(@RequestBody Medico newMedico){
        return this.medicoService.newMedico(newMedico);
    }

    @GetMapping("/all")
    public Iterable<Medico> getAllMedico(){
        return this.medicoService.getAllMedico();
    }

    @GetMapping("/{medico_id}")
    public Optional<Medico> getMedico(@PathVariable(value="medico_id") Long medico_id){
        return this.medicoService.getMedico(medico_id);
    }

    @PostMapping("/update")
    public Medico updateMedico(@RequestBody Medico medico){
        return this.medicoService.modifyMedico(medico);
    }

    @DeleteMapping(value = "/delete/{medico_id}")
    public Boolean deleteMedico(@PathVariable(value="medico_id") Long medico_id){
        return this.medicoService.deleteMedico(medico_id);
    }
}
