package psychOnline.psychonline.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psychOnline.psychonline.DTO.CitaDTO;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.model.Estado;
import psychOnline.psychonline.model.Medico;
import psychOnline.psychonline.repository.CitaRepository;
import psychOnline.psychonline.repository.EstadoRepository;
import psychOnline.psychonline.repository.MedicoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService{
    @Autowired
    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;
    private final EstadoRepository estadoRepository;
    private EntityManager entityManager;

    public CitaServiceImpl(CitaRepository citaRepository, MedicoRepository medicoRepository, EstadoRepository estadoRepository, EntityManager entityManager) {
        this.citaRepository = citaRepository;
        this.medicoRepository = medicoRepository;
        this.estadoRepository = estadoRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Cita newCita(Cita newCita) {
        return this.citaRepository.save(newCita);
    }

    public Cita crearCita(Long medicoId, LocalDateTime fechaHora){
        Medico medico = medicoRepository.findById(medicoId).orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        Estado estadoAgendada = estadoRepository.findByDescripcion("Agendada").orElseThrow(() -> new RuntimeException("Estado 'agendada' no encontrado"));

        Cita cita = Cita.builder()
                .medico(medico)
                .fecha_hora(fechaHora)
                .estado(estadoAgendada)
                .build();

        return citaRepository.save(cita);
    }

    @Override
    public String cancelarCita(Long citaId) {
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        Estado estadoCancelada = estadoRepository.findByDescripcion("Cancelada").orElseThrow(() -> new RuntimeException("Estado 'cancelada' no encontrado"));

        cita.setEstado(estadoCancelada);
        citaRepository.save(cita);

        return "La cancelación se realizó con éxito.";
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

    @Override
    @Transactional
    public List<CitaDTO> listarCitasPorMedico(Long medicoId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cita> query = cb.createQuery(Cita.class);
        Root<Cita> cita = query.from(Cita.class);

        cita.fetch("medico", JoinType.INNER);
        cita.fetch("paciente", JoinType.LEFT);
        cita.fetch("estado", JoinType.INNER);

        query.select(cita)
                .where(
                        cb.equal(cita.get("medico").get("medico_id"), medicoId),
                        cita.get("estado").get("descripcion").in("Finalizada", "Agendada", "Programada")
                );

        List<Cita> citas = entityManager.createQuery(query).getResultList();

        return citas.stream().map(c -> new CitaDTO(
                c.getCita_id(),
                c.getFecha_hora(),
                c.getPaciente() != null ? c.getPaciente().getNombre() + " " + c.getPaciente().getApellido() : null,
                c.getEstado().getDescripcion()
        )).collect(Collectors.toList());
    }
}
