package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psychOnline.psychonline.model.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByUsername(String usuario);

    @Query("SELECT m FROM Medico m LEFT JOIN FETCH m.especialidades")
    List<Medico> findAllWithEspecialidades();

    @Query("SELECT AVG(c.calificacion) FROM Comentario c WHERE c.medico.medico_id = :medicoId")
    Double findPromedioCalificaciones(@Param("medicoId") Long medicoId);
}
