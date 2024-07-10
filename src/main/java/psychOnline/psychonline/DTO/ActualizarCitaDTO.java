package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarCitaDTO {
    private Long pacienteId;
    private Long citaId;
    private String descripcionHistoria;
}
