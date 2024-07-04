package psychOnline.psychonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.model.Comentario;
import psychOnline.psychonline.service.ComentarioService;

import java.util.Optional;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/new")
    public Comentario newComentario(@RequestBody Comentario newComentario){
        return this.comentarioService.newComentario(newComentario);
    }

    @GetMapping("/all")
    public Iterable<Comentario> getAllComentario(){
        return this.comentarioService.getAllComentario();
    }

    @GetMapping("/{comentario_id}")
    public Optional<Comentario> getComentario(@PathVariable(value="comentario_id") Long comentario_id){
        return this.comentarioService.getComentario(comentario_id);
    }

    @PostMapping("/update")
    public Comentario updateComentario(@RequestBody Comentario comentario){
        return this.comentarioService.modifyComentario(comentario);
    }

    @DeleteMapping(value = "/delete/{comentario_id}")
    public Boolean deleteComentario(@PathVariable(value="comentario_id") Long comentario_id){
        return this.comentarioService.deleteComentario(comentario_id);
    }
}
