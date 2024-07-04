package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Cita;

import java.util.Optional;

public interface CitaService {
    Cita newCita(Cita newCita);
    Iterable<Cita> getAllCita();
    Optional<Cita> getCita(Long cita_id);
    Cita modifyCita(Cita cita);
    Boolean deleteCita(Long cita_id);
}
