package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psychOnline.psychonline.DTO.ComentarioDTO;
import psychOnline.psychonline.DTO.MedicoDTO;
import psychOnline.psychonline.DTO.MedicoEspecificoDTO;
import psychOnline.psychonline.DTO.MedicoPerfilDTO;
import psychOnline.psychonline.model.Medico;
import psychOnline.psychonline.repository.ComentarioRepository;
import psychOnline.psychonline.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService{
    @Autowired
    private final MedicoRepository medicoRepository;
    private final ComentarioRepository comentarioRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository, ComentarioRepository comentarioRepository) {
        this.medicoRepository = medicoRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public Medico newMedico(Medico newMedico) {
        return this.medicoRepository.save(newMedico);
    }

    @Override
    public Iterable<Medico> getAllMedico() {
        return this.medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> getMedico(Long medico_id) {
        return this.medicoRepository.findById(medico_id);
    }

    @Override
    public Medico modifyMedico(Medico medico) {
        Optional<Medico> medicoEncontrado = this.medicoRepository.findById(medico.getMedico_id());

        try{
            medicoEncontrado.get().setNombre(medico.getNombre());
            medicoEncontrado.get().setApellido(medico.getApellido());
            medicoEncontrado.get().setUsername(medico.getUsername());
            medicoEncontrado.get().setEmail(medico.getEmail());
            medicoEncontrado.get().setTelefono(medico.getTelefono());
            medicoEncontrado.get().setPassword(medico.getPassword());
            medicoEncontrado.get().setImagen(medico.getImagen());
            medicoEncontrado.get().setTitulos(medico.getTitulos());
            medicoEncontrado.get().setEstaVerificado(medico.getEstaVerificado());
            return this.newMedico(medicoEncontrado.get());
        }
        catch(Exception exc){
            return null;
        }
    }

    @Override
    public Boolean deleteMedico(Long medico_id) {
        this.medicoRepository.deleteById(medico_id);
        return true;
    }

    @Transactional(readOnly = true)
    public MedicoPerfilDTO obtenerPerfilMedico(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        List<String> especialidades = medico.getEspecialidades().stream()
                .map(especialidad -> especialidad.getNombre())
                .collect(Collectors.toList());

        return new MedicoPerfilDTO(
                medico.getImagen(),
                medico.getNombre() + " " + medico.getApellido(),
                medico.getUsername(),
                medico.getEmail(),
                medico.getTelefono(),
                especialidades,
                medico.getTitulos()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoDTO> obtenerTodosLosMedicos() {
        List<Medico> medicos = medicoRepository.findAllWithEspecialidades();

        return medicos.stream().map(medico -> {
            Double promedioCalificaciones = medicoRepository.findPromedioCalificaciones(medico.getMedico_id());
            List<String> especialidades = medico.getEspecialidades().stream()
                    .map(especialidad -> especialidad.getNombre())
                    .collect(Collectors.toList());

            return new MedicoDTO(
                    medico.getMedico_id(),
                    medico.getNombre() + " " + medico.getApellido(),
                    especialidades,
                    promedioCalificaciones != null ? promedioCalificaciones : 0.0
            );
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MedicoEspecificoDTO obtenerMedicoPorId(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        Double promedioCalificaciones = medicoRepository.findPromedioCalificaciones(medico.getMedico_id());
        List<String> especialidades = medico.getEspecialidades().stream()
                .map(especialidad -> especialidad.getNombre())
                .collect(Collectors.toList());

        List<ComentarioDTO> comentarios = comentarioRepository.findByMedicoId(medico.getMedico_id()).stream()
                .map(comentario -> new ComentarioDTO(comentario.getCalificacion(), comentario.getDescripcion()))
                .collect(Collectors.toList());

        return new MedicoEspecificoDTO(
                medico.getMedico_id(),
                medico.getNombre() + " " + medico.getApellido(),
                medico.getEmail(),
                especialidades,
                medico.getTitulos(),
                promedioCalificaciones != null ? promedioCalificaciones : 0.0,
                comentarios
        );
    }
}
