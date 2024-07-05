package psychOnline.psychonline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psychOnline.psychonline.config.AuthResponse;
import psychOnline.psychonline.config.LoginRequest;
import psychOnline.psychonline.config.RegisterRequest;
import psychOnline.psychonline.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(value = "/login/admin")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.loginAdmin(request));
    }

    @PostMapping(value = "/register/admin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }
}
