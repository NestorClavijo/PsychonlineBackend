package psychOnline.psychonline.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.DTO.*;
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

    @GetMapping("/{medico_id}/pasadas-programadas")
    public List<CitaDetalleDTO> obtenerCitasPasadasProgramadas(@PathVariable("medico_id") Long medicoId) {
        return citaService.listarCitasPasadasProgramadasPorMedico(medicoId);
    }

    @GetMapping("/{medico_id}/solicitadas")
    public List<CitaDetalleDTO> obtenerCitasSolicitadas(@PathVariable("medico_id") Long medicoId) {
        return citaService.listarCitasSolicitadasPorMedico(medicoId);
    }

    @PostMapping("/rechazar/{cita_id}")
    public ResponseEntity<String> rechazarCita(@PathVariable("cita_id") Long cita_id) {
        String mensaje = citaService.rechazarCita(cita_id);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/aceptar/{cita_id}")
    public ResponseEntity<String> aceptarCita(@PathVariable("cita_id") Long cita_id) {
        String mensaje = citaService.aceptarCita(cita_id);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/solicitar")
    public String solicitarCita(@RequestBody SolicitarCitaRequest request) {
        String cita = citaService.solicitarCita(request.getPacienteId(), request.getMedicoId(), request.getFechaHora());
        return cita;
    }
}
