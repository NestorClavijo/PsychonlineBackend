package psychOnline.psychonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.model.Historial;
import psychOnline.psychonline.repository.HistorialRepository;

import java.util.Optional;

@Service
public class HistorialServiceImpl implements HistorialService{
    @Autowired
    private HistorialRepository historialRepository;

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
}
