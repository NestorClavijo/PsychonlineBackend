package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Especialidad;
import psychOnline.psychonline.repository.EspecialidadRepository;

import java.util.Optional;

@Service
public class EspecialidaServiceImpl implements EspecialidadService{
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public Especialidad newEspecialidad(Especialidad newEspecialidad) {
        return this.especialidadRepository.save(newEspecialidad);
    }

    @Override
    public Iterable<Especialidad> getAllEspecialidad() {
        return this.especialidadRepository.findAll();
    }

    @Override
    public Optional<Especialidad> getEspecialidad(Long especialidad_id) {
        return this.especialidadRepository.findById(especialidad_id);
    }

    @Override
    public Especialidad modifyEspecialidad(Especialidad especialidad) {
        Optional<Especialidad> especialidadEncontrada = this.especialidadRepository.findById(especialidad.getEspecialidad_id());

        try{
            especialidadEncontrada.get().setNombre(especialidad.getNombre());
            return this.newEspecialidad(especialidadEncontrada.get());
        }
        catch(Exception exc){
            return null;
        }
    }

    @Override
    public Boolean deleteEspecialidad(Long especialidad_id) {
        this.especialidadRepository.deleteById(especialidad_id);
        return true;
    }
}
