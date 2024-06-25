package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Paciente;
import psychOnline.psychonline.service.PacienteService;

import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/new")
    public Paciente newPaciente(@RequestBody Paciente newPaciente){
        return this.pacienteService.newPaciente(newPaciente);
    }

    @GetMapping("/all")
    public Iterable<Paciente> getAll(){
        return this.pacienteService.getAll();
    }

    @GetMapping("/{paciente_id}")
    public Optional<Paciente> getPaciente(@PathVariable(value = "paciente_id") Long paciente_id){
        return this.pacienteService.getPaciente(paciente_id);
    }

    @PostMapping("/update")
    public Paciente updatePaciente(@RequestBody Paciente paciente){
        return this.pacienteService.modifyPaciente(paciente);
    }

    @DeleteMapping(value = "/delete/{paciente_id}")
    public Boolean deletePaciente(@PathVariable(value = "paciente_id") Long paciente_id){
        return this.pacienteService.deletePaciente(paciente_id);
    }
}
