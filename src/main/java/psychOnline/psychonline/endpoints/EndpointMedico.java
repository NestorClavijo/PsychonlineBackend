package psychOnline.psychonline.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psychOnline.psychonline.DTO.MedicoDTO;
import psychOnline.psychonline.DTO.MedicoPerfilDTO;
import psychOnline.psychonline.service.MedicoService;
import psychOnline.psychonline.DTO.MedicoEspecificoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/medico")
public class EndpointMedico {

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
