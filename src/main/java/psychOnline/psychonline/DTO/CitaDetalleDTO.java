package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDetalleDTO {
    private Long citaId;
    private Long pacienteId;
    private String nombreCompletoPaciente;
    private LocalDateTime fechaHora;
    private String mensaje;
}
