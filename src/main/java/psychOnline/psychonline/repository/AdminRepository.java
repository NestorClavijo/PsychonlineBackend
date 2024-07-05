package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychOnline.psychonline.model.Administrador;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Administrador,Long> {
    Optional<Administrador> findByUsername(String usuario);
}
