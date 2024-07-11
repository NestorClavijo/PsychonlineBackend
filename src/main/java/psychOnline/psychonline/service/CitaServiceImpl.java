package psychOnline.psychonline.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psychOnline.psychonline.DTO.*;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.model.Estado;
import psychOnline.psychonline.model.Medico;
import psychOnline.psychonline.model.Paciente;
import psychOnline.psychonline.repository.CitaRepository;
import psychOnline.psychonline.repository.EstadoRepository;
import psychOnline.psychonline.repository.MedicoRepository;
import psychOnline.psychonline.repository.PacienteRepository;

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
    private final PacienteRepository pacienteRepository;
    private final EntityManager entityManager;

    public CitaServiceImpl(CitaRepository citaRepository, MedicoRepository medicoRepository, EstadoRepository estadoRepository, PacienteRepository pacienteRepository, EntityManager entityManager) {
        this.citaRepository = citaRepository;
        this.medicoRepository = medicoRepository;
        this.estadoRepository = estadoRepository;
        this.pacienteRepository = pacienteRepository;
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
    public String rechazarCita(Long citaId) {
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        Estado estadoCancelada = estadoRepository.findByDescripcion("Rechazada").orElseThrow(() -> new RuntimeException("Estado 'cancelada' no encontrado"));

        cita.setEstado(estadoCancelada);
        citaRepository.save(cita);

        return "Se ha rechazado la cita con éxito.";
    }

    @Override
    public String programarCita(Long citaId, Long pacienteId) {
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Estado estadoProgramada = estadoRepository.findByDescripcion("Programada").orElseThrow(() -> new RuntimeException("Estado 'cancelada' no encontrado"));

        cita.setEstado(estadoProgramada);
        cita.setPaciente(paciente);
        citaRepository.save(cita);

        return "Se ha programado la cita con éxito.";
    }

    @Override
    public String aceptarCita(Long citaId) {
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        Estado estadoCancelada = estadoRepository.findByDescripcion("Programada").orElseThrow(() -> new RuntimeException("Estado 'cancelada' no encontrado"));

        cita.setEstado(estadoCancelada);
        citaRepository.save(cita);

        return "Se ha agendado la cita con éxito.";
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

    @Override
    @Transactional(readOnly = true)
    public List<PacienteDTO> listarPacientesPorMedico(Long medicoId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cita> query = cb.createQuery(Cita.class);
        Root<Cita> cita = query.from(Cita.class);

        cita.fetch("paciente", JoinType.INNER);
        cita.fetch("estado", JoinType.INNER);

        query.select(cita)
                .where(
                        cb.equal(cita.get("medico").get("medico_id"), medicoId),
                        cb.equal(cita.get("estado").get("descripcion"), "Finalizada")
                );

        List<Cita> citas = entityManager.createQuery(query).getResultList();

        return citas.stream()
                .map(Cita::getPaciente)
                .distinct()
                .map(p -> new PacienteDTO(
                        p.getPaciente_id(),
                        p.getNombre() + " " + p.getApellido(),
                        p.getEmail(),
                        p.getTelefono()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CitaDetalleDTO> listarCitasPasadasProgramadasPorMedico(Long medicoId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cita> query = cb.createQuery(Cita.class);
        Root<Cita> cita = query.from(Cita.class);

        cita.fetch("medico", JoinType.INNER);
        cita.fetch("paciente", JoinType.LEFT);
        cita.fetch("estado", JoinType.INNER);

        query.select(cita)
                .where(
                        cb.equal(cita.get("medico").get("medico_id"), medicoId),
                        cb.equal(cita.get("estado").get("descripcion"), "Programada"),
                        cb.lessThan(cita.get("fecha_hora"), LocalDateTime.now())
                );

        List<Cita> citas = entityManager.createQuery(query).getResultList();

        return citas.stream().map(c -> {
            if (c.getPaciente() == null) {
                return new CitaDetalleDTO(
                        c.getCita_id(),
                        null,
                        null,
                        c.getFecha_hora(),
                        "Paciente no existe"
                );
            } else {
                return new CitaDetalleDTO(
                        c.getCita_id(),
                        c.getPaciente().getPaciente_id(),
                        c.getPaciente().getNombre() + " " + c.getPaciente().getApellido(),
                        c.getFecha_hora(),
                        null
                );
            }
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CitaDetalleDTO> listarCitasSolicitadasPorMedico(Long medicoId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cita> query = cb.createQuery(Cita.class);
        Root<Cita> cita = query.from(Cita.class);

        cita.fetch("medico", JoinType.INNER);
        cita.fetch("paciente", JoinType.LEFT); // LEFT para permitir pacientes nulos
        cita.fetch("estado", JoinType.INNER);

        query.select(cita)
                .where(
                        cb.equal(cita.get("medico").get("medico_id"), medicoId),
                        cb.equal(cita.get("estado").get("descripcion"), "Solicitada")
                );

        List<Cita> citas = entityManager.createQuery(query).getResultList();

        return citas.stream().map(c -> {
            String nombreCompletoPaciente = c.getPaciente() != null ?
                    c.getPaciente().getNombre() + " " + c.getPaciente().getApellido() :
                    "Paciente no existe";

            return new CitaDetalleDTO(
                    c.getCita_id(),
                    c.getPaciente() != null ? c.getPaciente().getPaciente_id() : null,
                    nombreCompletoPaciente,
                    c.getFecha_hora(),
                    c.getEstado().getDescripcion()
            );
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CitaPacienteDTO> obtenerCitasPorPaciente(Long pacienteId) {
        List<Cita> citas = citaRepository.findCitasByPacienteId(pacienteId);

        return citas.stream().map(cita -> new CitaPacienteDTO(
                cita.getCita_id(),
                cita.getMedico().getNombre() + " " + cita.getMedico().getApellido(),
                cita.getFecha_hora(),
                cita.getEstado().getDescripcion()
        )).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String solicitarCita(Long pacienteId, Long medicoId, LocalDateTime fechaHora) {
        Paciente paciente = entityManager.find(Paciente.class, pacienteId);
        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado");
        }

        Medico medico = entityManager.find(Medico.class, medicoId);
        if (medico == null) {
            throw new RuntimeException("Medico no encontrado");
        }

        Estado estadoSolicitada = estadoRepository.findByDescripcion("Solicitada")
                .orElseThrow(() -> new RuntimeException("Estado 'Solicitada' no encontrado"));

        Cita cita = Cita.builder()
                .paciente(paciente)
                .medico(medico)
                .fecha_hora(fechaHora)
                .estado(estadoSolicitada)
                .build();

        entityManager.persist(cita);
        return "Cita solicitada con exito";
    }

    public List<CitaAgendadaDTO> obtenerCitasAgendadas() {
        List<Cita> citasAgendadas = citaRepository.findAllAgendadas();
        return citasAgendadas.stream().map(cita -> new CitaAgendadaDTO(
                cita.getCita_id(),
                cita.getMedico().getNombre() + " " + cita.getMedico().getApellido(),
                cita.getMedico().getEspecialidades().stream().map(especialidad -> especialidad.getNombre()).collect(Collectors.toList()),
                cita.getFecha_hora()
        )).collect(Collectors.toList());
    }
}
