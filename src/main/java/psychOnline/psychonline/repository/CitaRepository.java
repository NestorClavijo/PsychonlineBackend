package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychOnline.psychonline.model.Cita;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita,Long> {
}
