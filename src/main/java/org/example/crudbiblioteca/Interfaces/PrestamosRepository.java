package org.example.crudbiblioteca.Interfaces;

import org.example.crudbiblioteca.DTO.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamosRepository extends JpaRepository<Prestamo, String> {
}
