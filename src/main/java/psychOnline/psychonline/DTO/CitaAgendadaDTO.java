package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaAgendadaDTO {
    private Long citaId;
    private String nombreCompletoMedico;
    private List<String> especialidadesMedico;
    private LocalDateTime fechaHora;
}
