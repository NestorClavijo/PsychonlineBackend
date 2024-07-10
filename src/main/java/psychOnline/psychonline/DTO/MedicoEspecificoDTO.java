package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEspecificoDTO {
    private Long medicoId;
    private String nombreCompleto;
    private String email;
    private List<String> especialidades;
    private String titulos;
    private Double promedioCalificaciones;
    private List<ComentarioDTO> comentarios;
}
