package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Estado;
import psychOnline.psychonline.service.EstadoService;

import java.util.Optional;

@RestController
@RequestMapping("estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @PostMapping("/new")
    public Estado newEstado(@RequestBody Estado newEstado){
        return this.estadoService.newEstado(newEstado);
    }

    @GetMapping("/all")
    public Iterable<Estado> getAllEstado(){
        return this.estadoService.getAllEstado();
    }

    @GetMapping("/{estado_id}")
    public Optional<Estado> getEstado(@PathVariable(value="estado_id") Long estado_id){
        return this.estadoService.getEstado(estado_id);
    }

    @PostMapping("/update")
    public Estado updateEstado(@RequestBody Estado estado){
        return this.estadoService.modifyEstado(estado);
    }

    @DeleteMapping(value = "/delete/{estado_id}")
    public Boolean deleteEstado(@PathVariable(value="estado_id") Long estado_id){
        return this.estadoService.deleteEstado(estado_id);
    }
}
