package psychOnline.psychonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychOnline.psychonline.model.Paciente;

import java.util.Optional;

public interface PacienteRepository  extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByUsername(String usuario);
}
