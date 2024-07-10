package psychOnline.psychonline.service;

import psychOnline.psychonline.DTO.ActualizarCitaDTO;
import psychOnline.psychonline.DTO.NuevaHistoriaDTO;
import psychOnline.psychonline.model.Historial;

import java.util.Optional;

public interface HistorialService {
    Historial newHistorial(Historial newHistorial);
    Iterable<Historial> getAllHistorial();
    Optional<Historial> getHistorial(Long historial_id);
    Historial modifyHistorial(Historial historial);
    Boolean deleteHistorial(Long historial_id);

    void agregarNuevaHistoria(NuevaHistoriaDTO nuevaHistoriaDTO);
    void agregarHistoriaYActualizarCita(ActualizarCitaDTO actualizarCitaDTO);
}
