package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Paciente;

import java.util.Optional;

public interface PacienteService {
    Paciente newPaciente(Paciente newPaciente);
    Iterable<Paciente> getAll();
    Optional<Paciente> getPaciente(Long paciente_id);
    Paciente modifyPaciente(Paciente paciente);
    Boolean deletePaciente(Long paciente_id);
}