package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.repository.CitaRepository;

import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService{
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Cita newCita(Cita newCita) {
        return this.citaRepository.save(newCita);
    }

    @Override
    public Iterable<Cita> getAllCita() {
        return this.citaRepository.findAll();
    }

    @Override
    public Optional<Cita> getCita(Long cita_id) {
        return this.citaRepository.findById(cita_id);
    }

    @Override
    public Cita modifyCita(Cita cita) {
        Optional<Cita> CitaEncontrada = this.citaRepository.findById(cita.getCita_id());

        try{
            CitaEncontrada.get().setFecha_hora(cita.getFecha_hora());
            CitaEncontrada.get().setMedico(cita.getMedico());
            CitaEncontrada.get().setPaciente(cita.getPaciente());
            CitaEncontrada.get().setEstado(cita.getEstado());
            return this.newCita(CitaEncontrada.get());
        }
        catch(Exception exc){
            return null;
        }
    }

    @Override
    public Boolean deleteCita(Long cita_id) {
        this.citaRepository.deleteById(cita_id);
        return true;
    }
}
