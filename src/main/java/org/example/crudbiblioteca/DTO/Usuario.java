package org.example.crudbiblioteca.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Usuario", schema = "bibliotecaNueva")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 15)
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "dni", nullable = false, length = 15)
    @Pattern(regexp = "^[0-9]{8}[A-HJ-NP-TV-Z]$", message = "DNI INVÁLIDO")
    private String dni;

    @Size(max = 100)
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "nombre", nullable = false, length = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]{1,100}$", message = "NOMBRE INVÁLIDO")
    private String nombre;

    @Size(max = 100)
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "email", nullable = false, length = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]{1,50}@gmail.com", message = "EMAIL INVÁLIDO")
    private String email;

    @Size(max = 255)
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{4,12}", message = "PASSWORD INVÁLIDO")
    private String password;

    @NotNull
    @NotEmpty
    @NotBlank
    @Lob
    @Column(name = "tipo", nullable = false)
    @Pattern(regexp = "^(normal|administrador)", message = "TIPO INVÁLIDO")
    private String tipo;

    @Null
    @Column(name = "penalizacion_hasta", nullable = true)
    private LocalDate penalizacionHasta;

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %s, %s, %s", this.id, this.dni, this.nombre, this.email, this.password, this.tipo, this.penalizacionHasta);
    }
}