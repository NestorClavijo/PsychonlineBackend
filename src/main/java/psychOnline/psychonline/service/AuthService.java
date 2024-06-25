package psychOnline.psychonline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import psychOnline.psychonline.config.AuthResponse;
import psychOnline.psychonline.config.LoginRequest;
import psychOnline.psychonline.config.RegisterRequest;
import psychOnline.psychonline.model.Medico;
import psychOnline.psychonline.model.Paciente;
import psychOnline.psychonline.model.Role;
import psychOnline.psychonline.Jwt.JwtService;
import psychOnline.psychonline.repository.MedicoRepository;
import psychOnline.psychonline.repository.PacienteRepository;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){
        if (request.getRole().equals("PACIENTE")){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            UserDetails paciente=pacienteRepository.findByUsername(request.getUsername()).orElseThrow();
            String token=jwtService.getToken(paciente);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }
        else{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            UserDetails medico=medicoRepository.findByUsername(request.getUsername()).orElseThrow();
            String token=jwtService.getToken(medico);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }

    }

    public AuthResponse register(RegisterRequest request){
        if(request.getRole().equals("PACIENTE")){
            Paciente paciente = Paciente.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nombre(request.getNombre())
                    .apellido(request.getApellido())
                    .telefono(request.getTelefono())
                    .email(request.getEmail())
                    .role(Role.PACIENTE)
                    .build();

            pacienteRepository.save(paciente);

            return AuthResponse.builder()
                    .token(jwtService.getToken(paciente))
                    .build();
        }
        else{
            Medico medico = Medico.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nombre(request.getNombre())
                    .apellido(request.getApellido())
                    .telefono(request.getTelefono())
                    .imagen(request.getImagen())
                    .titulos(request.getTitulos())
                    .email(request.getEmail())
                    .role(Role.MEDICO)
                    .build();

            medicoRepository.save(medico);

            return AuthResponse.builder()
                    .token(jwtService.getToken(medico))
                    .build();
        }
    }
}
