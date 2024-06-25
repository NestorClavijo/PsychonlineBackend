package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Paciente;
import psychOnline.psychonline.repository.PacienteRepository;

import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService{
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente newPaciente(Paciente newPaciente) {
        return this.pacienteRepository.save(newPaciente);
    }

    @Override
    public Iterable<Paciente> getAll() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> getPaciente(Long paciente_id) {
        return this.pacienteRepository.findById(paciente_id);
    }

    @Override
    public Paciente modifyPaciente(Paciente paciente) {
        Optional<Paciente> pacienteEncontrado = this.pacienteRepository.findById(paciente.getPaciente_id());

        try{
            pacienteEncontrado.get().setNombre(paciente.getNombre());
            pacienteEncontrado.get().setApellido(paciente.getApellido());
            pacienteEncontrado.get().setUsername(paciente.getUsername());
            pacienteEncontrado.get().setEmail(paciente.getEmail());
            pacienteEncontrado.get().setTelefono(paciente.getTelefono());
            pacienteEncontrado.get().setPassword(paciente.getPassword());
            return this.newPaciente(pacienteEncontrado.get());
        }
        catch(Exception exc){
            return null;
        }
    }

    @Override
    public Boolean deletePaciente(Long paciente_id) {
        this.pacienteRepository.deleteById(paciente_id);
        return true;
    }
}
