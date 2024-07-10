package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearCitaRequest {
    private Long medicoId;
    private LocalDateTime fechaHora;
}
