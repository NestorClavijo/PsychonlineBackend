package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Historia;
import psychOnline.psychonline.repository.HistoriaRepository;

import java.util.Optional;

@Service
public class HistoriaServiceImpl implements HistoriaService {
    @Autowired
    private HistoriaRepository historiaRepository;

    @Override
    public Historia newHistoria(Historia newHistoria) {
        return this.historiaRepository.save(newHistoria);
    }

    @Override
    public Iterable<Historia> getAllHistoria() {
        return this.historiaRepository.findAll();
    }

    @Override
    public Optional<Historia> getHistoria(Long historia_id) {
        return this.historiaRepository.findById(historia_id);
    }

    @Override
    public Historia modifyHistoria(Historia historia) {
        Optional<Historia> HistoriaEncontrada = this.historiaRepository.findById(historia.getHistoria_id());

        try {
            HistoriaEncontrada.get().setDescripcion(historia.getDescripcion());
            return this.newHistoria(HistoriaEncontrada.get());
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public Boolean deleteHistoria(Long historia_id) {
        this.historiaRepository.deleteById(historia_id);
        return true;
    }
}
