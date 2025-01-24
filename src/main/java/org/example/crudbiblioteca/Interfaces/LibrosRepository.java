package org.example.crudbiblioteca.Interfaces;

import org.example.crudbiblioteca.DTO.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libro, String> {
}
