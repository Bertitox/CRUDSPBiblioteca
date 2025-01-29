package org.example.crudbiblioteca.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Libro", schema = "bibliotecaNueva")
@Data
public class Libro {
    @Id
    @Size(max = 20)
    @Column(name = "isbn", nullable = false, length = 20)
    @Pattern(regexp = "^(97[89])(-?\\d{1,5})(-?\\d{1,7})(-?\\d{1,7})(-?\\d{1})$", message = "ISBN INVÁLIDO")
    private String isbn;

    @Size(max = 200)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 200)
    @Pattern(regexp = "^[a-zA-Z0-9]{1,200}$", message = "TÍTULO INVÁLIDO")
    private String titulo;

    @Size(max = 100)
    @NotNull
    @Column(name = "autor", nullable = false, length = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]{1,100}$", message = "AUTOR INVÁLIDO")
    private String autor;

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s", this.isbn, this.titulo, this.autor);
    }

}