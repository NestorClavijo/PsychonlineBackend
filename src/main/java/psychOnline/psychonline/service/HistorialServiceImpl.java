package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psychOnline.psychonline.DTO.ActualizarCitaDTO;
import psychOnline.psychonline.DTO.NuevaHistoriaDTO;
import psychOnline.psychonline.model.*;
import psychOnline.psychonline.repository.*;

import java.util.Optional;

@Service
public class HistorialServiceImpl implements HistorialService{
    @Autowired
    private final HistorialRepository historialRepository;
    private final HistoriaRepository historiaRepository;
    private final PacienteRepository pacienteRepository;
    private final CitaRepository citaRepository;
    private final EstadoRepository estadoRepository;

    public HistorialServiceImpl(HistorialRepository historialRepository, HistoriaRepository historiaRepository, PacienteRepository pacienteRepository, CitaRepository citaRepository, EstadoRepository estadoRepository) {
        this.historialRepository = historialRepository;
        this.historiaRepository = historiaRepository;
        this.pacienteRepository = pacienteRepository;
        this.citaRepository = citaRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Historial newHistorial(Historial newHistorial) {
        return this.historialRepository.save(newHistorial);
    }

    @Override
    public Iterable<Historial> getAllHistorial() {
        return this.historialRepository.findAll();
    }

    @Override
    public Optional<Historial> getHistorial(Long historial_id) {
        return this.historialRepository.findById(historial_id);
    }

    @Override
    public Historial modifyHistorial(Historial historial) {
        Optional<Historial> HistorialEncontrado = this.historialRepository.findById(historial.getHistorial_id());

        try {
            HistorialEncontrado.get().setPaciente(historial.getPaciente());
            HistorialEncontrado.get().setHistoria(historial.getHistoria());
            return this.newHistorial(HistorialEncontrado.get());
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public Boolean deleteHistorial(Long historial_id) {
        this.historialRepository.deleteById(historial_id);
        return true;
    }

    @Transactional
    public void agregarNuevaHistoria(NuevaHistoriaDTO nuevaHistoriaDTO) {
        Paciente paciente = pacienteRepository.findById(nuevaHistoriaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Historia nuevaHistoria = Historia.builder()
                .descripcion(nuevaHistoriaDTO.getDescripcionHistoria())
                .build();
        historiaRepository.save(nuevaHistoria);

        Historial nuevoHistorial = Historial.builder()
                .paciente(paciente)
                .historia(nuevaHistoria)
                .build();
        historialRepository.save(nuevoHistorial);
    }

    @Transactional
    public void agregarHistoriaYActualizarCita(ActualizarCitaDTO actualizarCitaDTO) {
        Paciente paciente = pacienteRepository.findById(actualizarCitaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Cita cita = citaRepository.findById(actualizarCitaDTO.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        Historia nuevaHistoria = Historia.builder()
                .descripcion(actualizarCitaDTO.getDescripcionHistoria())
                .build();
        historiaRepository.save(nuevaHistoria);

        Historial nuevoHistorial = Historial.builder()
                .paciente(paciente)
                .historia(nuevaHistoria)
                .build();
        historialRepository.save(nuevoHistorial);

        Estado estadoFinalizado = estadoRepository.findByDescripcion("Finalizada")
                .orElseThrow(() -> new RuntimeException("Estado 'Finalizada' no encontrado"));
        cita.setEstado(estadoFinalizado);
        citaRepository.save(cita);
    }
}
