package psychOnline.psychonline.endpoints;

import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.DTO.MedicoDTO;
import psychOnline.psychonline.DTO.MedicoPerfilDTO;
import psychOnline.psychonline.service.MedicoService;
import psychOnline.psychonline.DTO.MedicoEspecificoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/medico")
@CrossOrigin(origins="http://localhost:4200")
public class
EndpointMedico {

    private final MedicoService medicoService;

    public EndpointMedico(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping("/perfil/{medicoId}")
    public MedicoPerfilDTO obtenerPerfilMedico(@PathVariable("medicoId") Long medicoId) {
        return medicoService.obtenerPerfilMedico(medicoId);
    }

    @GetMapping("/allmedicos")
    public List<MedicoDTO> obtenerTodosLosMedicos() {
        return medicoService.obtenerTodosLosMedicos();
    }

    @GetMapping("/perfil/{id}/especifico")
    public MedicoEspecificoDTO obtenerMedicoPorId(@PathVariable("id") Long medicoId) {
        return medicoService.obtenerMedicoPorId(medicoId);
    }

}
