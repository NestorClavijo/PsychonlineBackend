package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevaHistoriaDTO {
    private Long pacienteId;
    private String descripcionHistoria;
}
