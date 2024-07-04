package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Estado;

import java.util.Optional;

public interface EstadoService {
    Estado newEstado(Estado newEstado);
    Iterable<Estado> getAllEstado();
    Optional<Estado> getEstado(Long estado_id);
    Estado modifyEstado(Estado estado);
    Boolean deleteEstado(Long estado_id);
}
