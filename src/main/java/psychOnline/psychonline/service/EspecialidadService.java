package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Especialidad;

import java.util.Optional;

public interface EspecialidadService {
    Especialidad newEspecialidad(Especialidad newEspecialidad);
    Iterable<Especialidad> getAllEspecialidad();
    Optional<Especialidad> getEspecialidad(Long especialidad_id);
    Especialidad modifyEspecialidad(Especialidad especialidad);
    Boolean deleteEspecialidad(Long especialidad_id);
}
