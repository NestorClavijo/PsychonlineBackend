package psychOnline.psychonline.service;

import psychOnline.psychonline.DTO.CitaDTO;
import psychOnline.psychonline.DTO.PacienteDTO;
import psychOnline.psychonline.model.Cita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    Cita crearCita(Long medicoId, LocalDateTime fechaHora);
    String cancelarCita(Long citaID);
    List<CitaDTO> listarCitasPorMedico(Long medicoId);
    List<PacienteDTO> listarPacientesPorMedico(Long medicoId);
    Cita newCita(Cita newCita);
    Iterable<Cita> getAllCita();
    Optional<Cita> getCita(Long cita_id);
    Cita modifyCita(Cita cita);
    Boolean deleteCita(Long cita_id);
}
