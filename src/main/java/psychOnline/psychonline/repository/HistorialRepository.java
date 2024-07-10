package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psychOnline.psychonline.model.Historial;

import java.util.List;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    @Query("SELECT h FROM Historial h WHERE h.paciente.paciente_id = :pacienteId")
    List<Historial> findByPacienteId(@Param("pacienteId") Long pacienteId);
}
