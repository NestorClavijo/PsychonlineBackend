package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaPacienteDTO {
    private Long citaId;
    private String nombreCompletoMedico;
    private LocalDateTime fechaHora;
    private String estado;
}
