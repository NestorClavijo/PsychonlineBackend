package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CitaDTO {
    private Long citaId;
    private LocalDateTime fechaHora;
    private String nombrePaciente;
    private String estado;
}
