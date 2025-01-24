package org.example.crudbiblioteca.Interfaces;

import org.example.crudbiblioteca.DTO.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplaresRepository extends JpaRepository<Ejemplar, String> {
}
