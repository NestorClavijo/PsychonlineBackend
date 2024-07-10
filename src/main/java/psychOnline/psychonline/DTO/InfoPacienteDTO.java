package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPacienteDTO {
    private Long pacienteId;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private List<String> historias;
}
