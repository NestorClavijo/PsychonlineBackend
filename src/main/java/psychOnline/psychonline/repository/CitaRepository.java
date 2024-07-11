package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psychOnline.psychonline.model.Cita;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita,Long> {
    @Query("SELECT c FROM Cita c WHERE c.paciente.paciente_id = :pacienteId")
    List<Cita> findCitasByPacienteId(@Param("pacienteId") Long pacienteId);

    @Query("SELECT c FROM Cita c WHERE c.estado.descripcion = 'Agendada'")
    List<Cita> findAllAgendadas();
}
