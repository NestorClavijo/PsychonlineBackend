package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.model.Especialidad;
import psychOnline.psychonline.service.EspecialidadService;

import java.util.Optional;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping("/new")
    public Especialidad newEspecialidad(@RequestBody Especialidad newEspecialidad){
        return this.especialidadService.newEspecialidad(newEspecialidad);
    }

    @GetMapping("/all")
    public Iterable<Especialidad> getAllEspecialidad(){
        return this.especialidadService.getAllEspecialidad();
    }

    @GetMapping("/{especialidad_id}")
    public Optional<Especialidad> getEspecialidad(@PathVariable(value="especialidad_id") Long especialidad_id){
        return this.especialidadService.getEspecialidad(especialidad_id);
    }

    @PostMapping("/update")
    public Especialidad updateEspecialidad(@RequestBody Especialidad especialidad){
        return this.especialidadService.modifyEspecialidad(especialidad);
    }

    @DeleteMapping(value = "/delete/{especialidad_id}")
    public Boolean deleteEspecialidad(@PathVariable(value="especialidad_id") Long especialidad_id){
        return this.especialidadService.deleteEspecialidad(especialidad_id);
    }
}
