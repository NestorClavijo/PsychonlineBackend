package psychOnline.psychonline.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    Long paciente_id;
    String nombre;
    String apellido;
    String email;
    String telefono;
    String imagen;
    String titulos;
    String username;
    String password;
}
