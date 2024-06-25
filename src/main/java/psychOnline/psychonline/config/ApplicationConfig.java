package psychOnline.psychonline.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import psychOnline.psychonline.repository.MedicoRepository;
import psychOnline.psychonline.repository.PacienteRepository;

import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    @Bean
    public AuthenticationManager authenticationManagerMedico(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailService() {
        //return usuario -> pacienteRepository.findByUsername(usuario).orElseThrow(()-> new UsernameNotFoundException("El usuario no fue encontrado"));
        return username -> {
            UserDetails user = pacienteRepository.findByUsername(username).orElse(null);
            if (user == null) {
                user = medicoRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado"));
            }
            return user;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
