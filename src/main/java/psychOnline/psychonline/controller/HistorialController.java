package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Historial;
import psychOnline.psychonline.service.HistorialService;

import java.util.Optional;

@RestController
@RequestMapping("/historial")
public class HistorialController {
    @Autowired
    private HistorialService historialService;

    @PostMapping("/new")
    public Historial newHistorial(@RequestBody Historial newHistorial){
        return this.historialService.newHistorial(newHistorial);
    }

    @GetMapping("/all")
    public Iterable<Historial> getAllHistorial(){
        return this.historialService.getAllHistorial();
    }

    @GetMapping("/{historial_id}")
    public Optional<Historial> getHistorial(@PathVariable(value="historial_id") Long historial_id){
        return this.historialService.getHistorial(historial_id);
    }

    @PostMapping("/update")
    public Historial updateHistorial(@RequestBody Historial historial){
        return this.historialService.modifyHistorial(historial);
    }

    @DeleteMapping(value = "/delete/{historial_id}")
    public Boolean deleteHistorial(@PathVariable(value="historial_id") Long historial_id){
        return this.historialService.deleteHistorial(historial_id);
    }
}
