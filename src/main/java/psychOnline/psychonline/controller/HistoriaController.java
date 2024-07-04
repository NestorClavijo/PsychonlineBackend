package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Historia;
import psychOnline.psychonline.service.HistoriaService;

import java.util.Optional;

@RestController
@RequestMapping("/historia")
public class HistoriaController {
    @Autowired
    private HistoriaService historiaService;

    @PostMapping("/new")
    public Historia newHistoria(@RequestBody Historia newHistoria){
        return this.historiaService.newHistoria(newHistoria);
    }

    @GetMapping("/all")
    public Iterable<Historia> getAllHistoria(){
        return this.historiaService.getAllHistoria();
    }

    @GetMapping("/{historia_id}")
    public Optional<Historia> getHistoria(@PathVariable(value="historia_id") Long historia_id){
        return this.historiaService.getHistoria(historia_id);
    }

    @PostMapping("/update")
    public Historia updateHistoria(@RequestBody Historia historia){
        return this.historiaService.modifyHistoria(historia);
    }

    @DeleteMapping(value = "/delete/{historia_id}")
    public Boolean deleteHistoria(@PathVariable(value="historia_id") Long historia_id){
        return this.historiaService.deleteHistoria(historia_id);
    }
}
