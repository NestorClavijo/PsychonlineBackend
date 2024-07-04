package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Comentario;

import java.util.Optional;

public interface ComentarioService {
    Comentario newComentario(Comentario newComentario);
    Iterable<Comentario> getAllComentario();
    Optional<Comentario> getComentario(Long comentario_id);
    Comentario modifyComentario(Comentario comentario);
    Boolean deleteComentario(Long comentario_id);
}
