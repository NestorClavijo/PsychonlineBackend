package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Comentario;
import psychOnline.psychonline.repository.ComentarioRepository;

import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService{
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario newComentario(Comentario newComentario) {
        return this.comentarioRepository.save(newComentario);
    }

    @Override
    public Iterable<Comentario> getAllComentario() {
        return this.comentarioRepository.findAll();
    }

    @Override
    public Optional<Comentario> getComentario(Long comentario_id) {
        return this.comentarioRepository.findById(comentario_id);
    }

    @Override
    public Comentario modifyComentario(Comentario comentario) {
        Optional<Comentario> ComentarioEncontrado = this.comentarioRepository.findById(comentario.getComentario_id());

        try{
            ComentarioEncontrado.get().setMedico(comentario.getMedico());
            ComentarioEncontrado.get().setPaciente(comentario.getPaciente());
            ComentarioEncontrado.get().setDescripcion(comentario.getDescripcion());
            ComentarioEncontrado.get().setCalificacion(comentario.getCalificacion());
            return this.newComentario(ComentarioEncontrado.get());
        }
        catch(Exception exc){
            return null;
        }
    }

    @Override
    public Boolean deleteComentario(Long comentario_id) {
        this.comentarioRepository.deleteById(comentario_id);
        return true;
    }
}
