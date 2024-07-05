package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Medico;
import psychOnline.psychonline.repository.MedicoRepository;

import java.util.Optional;

@Service
public class MedicoServiceImpl implements MedicoService{
    @Autowired
    private MedicoRepository medicoRepository;

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
}
