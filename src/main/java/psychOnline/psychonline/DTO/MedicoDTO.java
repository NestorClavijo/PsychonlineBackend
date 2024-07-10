package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {
    private Long medicoId;
    private String nombreCompleto;
    private List<String> especialidades;
    private Double promedioCalificaciones;
}
