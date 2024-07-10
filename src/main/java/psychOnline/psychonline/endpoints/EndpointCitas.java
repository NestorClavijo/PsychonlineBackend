package psychOnline.psychonline.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.DTO.CitaDTO;
import psychOnline.psychonline.DTO.CrearCitaRequest;
import psychOnline.psychonline.DTO.PacienteDTO;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("api/cita")
public class EndpointCitas {

    @Autowired
    private final CitaService citaService;

    public EndpointCitas(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Cita> crearCita(@RequestBody CrearCitaRequest request) {
        Cita nuevaCita = citaService.crearCita(request.getMedicoId(), request.getFechaHora());
        return ResponseEntity.ok(nuevaCita);
    }

    @PostMapping("/cancelar/{cita_id}")
    public ResponseEntity<String> cancelarCita(@PathVariable("cita_id") Long cita_id) {
        String mensaje = citaService.cancelarCita(cita_id);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/medico/{medicoId}")
    public List<CitaDTO> listarCitasPorMedico(@PathVariable("medicoId") Long medico_id) {
        return citaService.listarCitasPorMedico(medico_id);
    }

    @GetMapping("/medico/{medicoId}/pacientes")
    public List<PacienteDTO> listarPacientesPorMedico(@PathVariable("medicoId") Long medico_id) {
        return citaService.listarPacientesPorMedico(medico_id);
    }
}
