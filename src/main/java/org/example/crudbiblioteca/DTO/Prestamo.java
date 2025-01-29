package org.example.crudbiblioteca.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Prestamo", schema = "bibliotecaNueva")
@Data
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    //EVITAR COMPLEJIDAD CICLOMÁTICA
    //@JsonIgnore
    @JsonIncludeProperties({"id"})
    private Usuario usuario;

    @NotNull
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ejemplar_id", nullable = false)
    //EVITAR COMPLEJIDAD CICLOMÁTICA
    //@JsonIgnore
    @JsonIncludeProperties({"id"})
    private Ejemplar ejemplar;


    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s", this.id, this.usuario.getId(), this.fechaInicio, this.fechaDevolucion);
    }
}