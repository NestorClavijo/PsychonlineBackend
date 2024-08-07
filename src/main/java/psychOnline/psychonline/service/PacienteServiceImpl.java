package psychOnline.psychonline.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.DTO.InfoPacienteDTO;
import psychOnline.psychonline.DTO.PacientePerfilDTO;
import psychOnline.psychonline.model.Historial;
import psychOnline.psychonline.model.Paciente;
import psychOnline.psychonline.repository.HistorialRepository;
import psychOnline.psychonline.repository.PacienteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService{
    @Autowired
    private final PacienteRepository pacienteRepository;
    private final HistorialRepository historialRepository;
    private final EntityManager entityManager;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, HistorialRepository historialRepository, EntityManager entityManager) {
        this.pacienteRepository = pacienteRepository;
        this.historialRepository = historialRepository;
        this.entityManager = entityManager;
    }

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

    @Transactional(readOnly = true)
    public InfoPacienteDTO obtenerInformacionPaciente(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(historialRepository != null){
            List<Historial> historiales = historialRepository.findByPacienteId(pacienteId);

            List<String> historias = historiales != null ? historiales.stream()
                    .map(historial -> historial.getHistoria() != null
                            ? historial.getHistoria().getDescripcion()
                            : "No hay historia")
                    .collect(Collectors.toList()) : List.of("No hay historias");

            InfoPacienteDTO pacienteDTO = new InfoPacienteDTO();
            pacienteDTO.setPacienteId(paciente.getPaciente_id());
            pacienteDTO.setNombreCompleto(paciente.getNombre() + " " + paciente.getApellido());
            pacienteDTO.setEmail(paciente.getEmail());
            pacienteDTO.setTelefono(paciente.getTelefono());
            pacienteDTO.setHistorias(historias);

            return pacienteDTO;
        }
        else {
            InfoPacienteDTO pacienteDTO = new InfoPacienteDTO();
            pacienteDTO.setPacienteId(paciente.getPaciente_id());
            pacienteDTO.setNombreCompleto(paciente.getNombre() + " " + paciente.getApellido());
            pacienteDTO.setEmail(paciente.getEmail());
            pacienteDTO.setTelefono(paciente.getTelefono());
            pacienteDTO.setHistorias(null);

            return pacienteDTO;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PacientePerfilDTO obtenerPerfilUsuario(Long usuarioId) {
        Paciente paciente = entityManager.find(Paciente.class, usuarioId);
        if (paciente == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        List<String> historias = historialRepository.findByPacienteId(usuarioId).stream()
                .map(historial -> historial.getHistoria().getDescripcion())
                .collect(Collectors.toList());

        return new PacientePerfilDTO(
                paciente.getNombre() + " " + paciente.getApellido(),
                paciente.getUsername(),
                paciente.getEmail(),
                paciente.getTelefono(),
                historias
        );
    }
}
