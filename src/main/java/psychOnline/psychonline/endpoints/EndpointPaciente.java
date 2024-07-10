package psychOnline.psychonline.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psychOnline.psychonline.DTO.CitaPacienteDTO;
import psychOnline.psychonline.DTO.InfoPacienteDTO;
import psychOnline.psychonline.DTO.PacientePerfilDTO;
import psychOnline.psychonline.service.CitaService;
import psychOnline.psychonline.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("api/paciente")
public class EndpointPaciente {

    private final PacienteService pacienteService;
    private final CitaService citaService;

    public EndpointPaciente(PacienteService pacienteService, CitaService citaService) {
        this.pacienteService = pacienteService;
        this.citaService = citaService;
    }

    @GetMapping("/{paciente_id}")
    public InfoPacienteDTO obtenerInformacionPaciente(@PathVariable("paciente_id") Long pacienteId) {
        return pacienteService.obtenerInformacionPaciente(pacienteId);
    }

    @GetMapping("{pacienteId}/citas")
    public List<CitaPacienteDTO> obtenerCitasPorPaciente(@PathVariable Long pacienteId) {
        return citaService.obtenerCitasPorPaciente(pacienteId);
    }

    @GetMapping("/perfil/{pacienteId}")
    public ResponseEntity<PacientePerfilDTO> obtenerPerfilUsuario(@PathVariable("pacienteId") Long usuarioId) {
        PacientePerfilDTO perfil = pacienteService.obtenerPerfilUsuario(usuarioId);
        return ResponseEntity.ok(perfil);
    }
}
