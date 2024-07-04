package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychOnline.psychonline.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
