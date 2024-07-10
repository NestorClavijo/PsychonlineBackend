package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.model.Comentario;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    @Query("SELECT c FROM Comentario c WHERE c.medico.medico_id = :medicoId")
    List<Comentario> findByMedicoId(@Param("medicoId") Long medicoId);
}
