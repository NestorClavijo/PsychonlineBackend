package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychOnline.psychonline.model.Estado;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
    Optional<Estado> findByDescripcion(String descripcion);
}
