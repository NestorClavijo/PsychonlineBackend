package psychOnline.psychonline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacientePerfilDTO {
    private String nombreCompleto;
    private String username;
    private String email;
    private String telefono;
    private List<String> historias;
}
