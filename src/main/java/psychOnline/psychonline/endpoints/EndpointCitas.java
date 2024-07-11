package psychOnline.psychonline.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.DTO.*;
import psychOnline.psychonline.model.Cita;
import psychOnline.psychonline.service.CitaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cita")
@CrossOrigin(origins="http://localhost:4200")
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
    public ResponseEntity<Map<String, String>> cancelarCita(@PathVariable("cita_id") Long cita_id) {
        String mensaje = citaService.cancelarCita(cita_id);
        Map<String, String> response = new HashMap<>();
        response.put("message", mensaje);
        return ResponseEntity.ok(response);
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
    public ResponseEntity<Map<String, String>> rechazarCita(@PathVariable("cita_id") Long cita_id) {
        String mensaje = citaService.rechazarCita(cita_id);
        Map<String, String> response = new HashMap<>();
        response.put("message", mensaje);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/aceptar/{cita_id}")
    public ResponseEntity<Map<String, String>> aceptarCita(@PathVariable("cita_id") Long cita_id) {
        String mensaje = citaService.aceptarCita(cita_id);
        Map<String, String> response = new HashMap<>();
        response.put("message", mensaje);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/solicitar")
    public ResponseEntity<Map<String, String>> solicitarCita(@RequestBody SolicitarCitaRequest request) {
        String cita = citaService.solicitarCita(request.getPacienteId(), request.getMedicoId(), request.getFechaHora());
        Map<String, String> response = new HashMap<>();
        response.put("message", cita);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/agendadas")
    public List<CitaAgendadaDTO> listarCitasAgendadas() {
        return citaService.obtenerCitasAgendadas();
    }

    @PostMapping("/programar")
    public ResponseEntity<Map<String, String>> programarCita(@RequestBody ProgramarCitaRequest request) {
        String mensaje = citaService.programarCita(request.getCita_id(), request.getPaciente_id());
        Map<String, String> response = new HashMap<>();
        response.put("message", mensaje);
        return ResponseEntity.ok(response);
    }
}
