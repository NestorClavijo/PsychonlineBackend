package psychOnline.psychonline.service;

import psychOnline.psychonline.DTO.*;
import psychOnline.psychonline.model.Cita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    Cita newCita(Cita newCita);
    Iterable<Cita> getAllCita();
    Optional<Cita> getCita(Long cita_id);
    Cita modifyCita(Cita cita);
    Boolean deleteCita(Long cita_id);

    Cita crearCita(Long medicoId, LocalDateTime fechaHora);
    String cancelarCita(Long citaID);
    String programarCita(Long citaID, Long pacienteID);
    List<CitaDTO> listarCitasPorMedico(Long medicoId);
    List<PacienteDTO> listarPacientesPorMedico(Long medicoId);
    List<CitaDetalleDTO> listarCitasPasadasProgramadasPorMedico(Long medicoId);
    List<CitaDetalleDTO> listarCitasSolicitadasPorMedico(Long medicoId);
    String rechazarCita(Long citaID);
    String aceptarCita(Long citaID);
    List<CitaPacienteDTO> obtenerCitasPorPaciente(Long pacienteId);
    String solicitarCita(Long pacienteId, Long medicoId, LocalDateTime fechaHora);
    List<CitaAgendadaDTO> obtenerCitasAgendadas();
}
