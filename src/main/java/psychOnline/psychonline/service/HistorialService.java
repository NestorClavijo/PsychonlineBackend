package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Historial;

import java.util.Optional;

public interface HistorialService {
    Historial newHistorial(Historial newHistorial);
    Iterable<Historial> getAllHistorial();
    Optional<Historial> getHistorial(Long historial_id);
    Historial modifyHistorial(Historial historial);
    Boolean deleteHistorial(Long historial_id);
}
