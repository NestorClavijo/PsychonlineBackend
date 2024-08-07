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
import psychOnline.psychonline.model.Administrador;
import psychOnline.psychonline.model.Medico;
import psychOnline.psychonline.model.Paciente;
import psychOnline.psychonline.model.Role;
import psychOnline.psychonline.Jwt.JwtService;
import psychOnline.psychonline.repository.AdminRepository;
import psychOnline.psychonline.repository.MedicoRepository;
import psychOnline.psychonline.repository.PacienteRepository;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse loginPaciente(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails paciente=pacienteRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(paciente);
        long id= ((Paciente) paciente).getPaciente_id();
        return AuthResponse.builder()
                .token(token)
                .id(id)
                .build();
    }

    public AuthResponse loginMedico(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails medico=medicoRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(medico);
        long id=((Medico) medico).getMedico_id();
        return AuthResponse.builder()
                .token(token)
                .id(id)
                .build();

    }

    public AuthResponse registerPaciente(RegisterRequest request){
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
        long id= ((Paciente) paciente).getPaciente_id();

        return AuthResponse.builder()
                .token(jwtService.getToken(paciente))
                .id(id)
                .build();

    }

    public AuthResponse registerMedico(RegisterRequest request){

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
                .estaVerificado(false)
                .build();

        medicoRepository.save(medico);

        long id=((Medico) medico).getMedico_id();

        return AuthResponse.builder()
                .token(jwtService.getToken(medico))
                .id(id)
                .build();
    }

    public AuthResponse loginAdmin(RegisterRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails admin = (UserDetails) adminRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(admin);

        long id =((Administrador) admin).getAdministrador_id();
        return AuthResponse.builder()
                .token(token)
                .id(id)
                .build();

    }

    public AuthResponse registerAdmin(RegisterRequest request){

        Administrador admin = Administrador.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        adminRepository.save(admin);

        long id =((Administrador) admin).getAdministrador_id();

        return AuthResponse.builder()
                .token(jwtService.getToken(admin))
                .id(id)
                .build();

    }
}
