package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychOnline.psychonline.model.Historial;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
}
