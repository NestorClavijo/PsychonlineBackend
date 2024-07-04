package psychOnline.psychonline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "historia")

public class Historia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historia_id;

    @Column(name = "descripcion",length=1024 ,nullable=false)
    private String descripcion;
}
