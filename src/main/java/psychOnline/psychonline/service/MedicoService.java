package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Medico;

import java.util.Optional;

public interface MedicoService {
    Medico newMedico(Medico newMedico);
    Iterable<Medico> getAllMedico();
    Optional<Medico> getMedico(Long medico_id);
    Medico modifyMedico(Medico medico);
    Boolean deleteMedico(Long medico_id);
}
