package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.service.CitaService;

import java.util.Optional;

@RestController
@RequestMapping("/cita/prueba")
public class CitaController {
    //@Autowired
    //private CitaService citaService;

    //@PostMapping("/new")
    //public Cita newCita(@RequestBody Cita newCita){
    //    return this.citaService.newCita(newCita);
    //}

    //@GetMapping("/all")
    //public Iterable<Cita> getAllCita(){
    //    return this.citaService.getAllCita();
    //}

    //@GetMapping("/{cita_id}")
    //public Optional<Cita> getCita(@PathVariable(value="cita_id") Long cita_id){
    //    return this.citaService.getCita(cita_id);
    //}

    //@PostMapping("/update")
    //public Cita updateCita(@RequestBody Cita cita){
    //    return this.citaService.modifyCita(cita);
    //}

    //@DeleteMapping(value = "/delete/{cita_id}")
    //public Boolean deleteCita(@PathVariable(value="cita_id") Long cita_id){
    //    return this.citaService.deleteCita(cita_id);
    //}
}
