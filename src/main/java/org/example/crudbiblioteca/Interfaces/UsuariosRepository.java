package org.example.crudbiblioteca.Interfaces;

import org.example.crudbiblioteca.DTO.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, String> {
}
