package psychOnline.psychonline.service;

import psychOnline.psychonline.DTO.MedicoDTO;
import psychOnline.psychonline.DTO.MedicoEspecificoDTO;
import psychOnline.psychonline.DTO.MedicoPerfilDTO;
import psychOnline.psychonline.model.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
    Medico newMedico(Medico newMedico);
    Iterable<Medico> getAllMedico();
    Optional<Medico> getMedico(Long medico_id);
    Medico modifyMedico(Medico medico);
    Boolean deleteMedico(Long medico_id);

    MedicoPerfilDTO obtenerPerfilMedico(Long medicoId);
    List<MedicoDTO> obtenerTodosLosMedicos();
    MedicoEspecificoDTO obtenerMedicoPorId(Long medicoId);
}
