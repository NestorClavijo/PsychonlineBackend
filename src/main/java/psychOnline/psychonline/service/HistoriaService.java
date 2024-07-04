package psychOnline.psychonline.service;

import psychOnline.psychonline.model.Historia;

import java.util.Optional;

public interface HistoriaService {
    Historia newHistoria(Historia newHistoria);
    Iterable<Historia> getAllHistoria();
    Optional<Historia> getHistoria(Long historia_id);
    Historia modifyHistoria(Historia historia);
    Boolean deleteHistoria(Long historia_id);
}
