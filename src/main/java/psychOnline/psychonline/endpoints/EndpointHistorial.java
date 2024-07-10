package psychOnline.psychonline.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psychOnline.psychonline.DTO.ActualizarCitaDTO;
import psychOnline.psychonline.DTO.NuevaHistoriaDTO;
import psychOnline.psychonline.service.HistorialService;

@RestController
@RequestMapping("api/historial")
public class EndpointHistorial {
    @Autowired
    private HistorialService historialService;

    @PostMapping("/nueva")
    public void agregarNuevaHistoria(@RequestBody NuevaHistoriaDTO nuevaHistoriaDTO) {
        historialService.agregarNuevaHistoria(nuevaHistoriaDTO);
    }

    @PostMapping("/actualizar-cita")
    public void agregarHistoriaYActualizarCita(@RequestBody ActualizarCitaDTO actualizarCitaDTO) {
        historialService.agregarHistoriaYActualizarCita(actualizarCitaDTO);
    }
}
