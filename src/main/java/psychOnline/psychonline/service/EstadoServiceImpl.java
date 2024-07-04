package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Estado;
import psychOnline.psychonline.repository.EstadoRepository;

import java.util.Optional;

@Service
public class EstadoServiceImpl implements EstadoService{
    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public Estado newEstado(Estado newEstado) {
        return this.estadoRepository.save(newEstado);
    }

    @Override
    public Iterable<Estado> getAllEstado() {
        return this.estadoRepository.findAll();
    }

    @Override
    public Optional<Estado> getEstado(Long estado_id) {
        return this.estadoRepository.findById(estado_id);
    }

    @Override
    public Estado modifyEstado(Estado estado) {
        Optional<Estado> estadoEncontrado = this.estadoRepository.findById(estado.getEstado_id());

        try{
            estadoEncontrado.get().setDescripcion(estado.getDescripcion());
            return this.newEstado(estadoEncontrado.get());
        }
        catch(Exception exc){
            return null;
        }
    }

    @Override
    public Boolean deleteEstado(Long estado_id) {
        this.estadoRepository.deleteById(estado_id);
        return true;
    }
}
