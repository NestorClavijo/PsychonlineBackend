package psychOnline.psychonline.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psychOnline.psychonline.DTO.InfoPacienteDTO;
import psychOnline.psychonline.service.PacienteService;

@RestController
@RequestMapping("api/paciente")
public class EndpointPaciente {
    private final PacienteService pacienteService;

    public EndpointPaciente(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{paciente_id}")
    public InfoPacienteDTO obtenerInformacionPaciente(@PathVariable("paciente_id") Long pacienteId) {
        return pacienteService.obtenerInformacionPaciente(pacienteId);
    }
}
