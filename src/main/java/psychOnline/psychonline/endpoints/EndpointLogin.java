package psychOnline.psychonline.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psychOnline.psychonline.config.AuthResponse;
import psychOnline.psychonline.config.LoginRequest;
import psychOnline.psychonline.config.RegisterRequest;
import psychOnline.psychonline.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:4200")
public class EndpointLogin {
    private final AuthService authService;

    @PostMapping(value = "/login/medico")
    public ResponseEntity<AuthResponse> loginMedico(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.loginMedico(request));
    }

    @PostMapping(value = "/login/paciente")
    public ResponseEntity<AuthResponse> loginPaciente(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.loginPaciente(request));
    }

    @PostMapping(value = "/login/admin")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.loginAdmin(request));
    }

    @PostMapping(value = "/register/medico")
    public ResponseEntity<AuthResponse> registerMedico(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.registerMedico(request));
    }

    @PostMapping(value = "/register/paciente")
    public ResponseEntity<AuthResponse> registerPaciente(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.registerPaciente(request));
    }

    @PostMapping(value = "/register/admin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }
}
