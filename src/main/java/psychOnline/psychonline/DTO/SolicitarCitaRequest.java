package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitarCitaRequest {
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime fechaHora;
}
