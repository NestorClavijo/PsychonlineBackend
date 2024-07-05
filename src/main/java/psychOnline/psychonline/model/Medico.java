package psychOnline.psychonline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medico")
public class Medico implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medico_id;

    @Column(name = "nombre",length=255 ,nullable=false)
    private String nombre;

    @Column(name = "apellido",length=255 ,nullable=false)
    private String apellido;

    @Column(name = "email",length=255 ,nullable=false, unique = true)
    private String email;

    @Column(name = "telefono",length=20 ,nullable=false)
    private String telefono;

    @Column(name = "imagen",columnDefinition = "TEXT")
    private String imagen;

    @Column(name = "titulos",columnDefinition = "TEXT")
    private String titulos;

    @Column(name = "username",length=255 ,nullable=false,unique = true)
    private String username;

    @Column(name = "password",length=255 ,nullable=false)
    private String password;

    @Column(name = "estaVerificado", nullable = false)
    private Boolean estaVerificado;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "Medico_Especialidad",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private Set<Especialidad> especialidades;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
