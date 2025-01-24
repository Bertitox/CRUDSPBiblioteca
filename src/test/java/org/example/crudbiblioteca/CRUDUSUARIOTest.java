package org.example.crudbiblioteca;

import org.example.crudbiblioteca.Controladores.CRUDUSUARIO;
import org.example.crudbiblioteca.DTO.Usuario;
import org.example.crudbiblioteca.Interfaces.UsuariosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CRUDUSUARIOTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @InjectMocks
    private CRUDUSUARIO crudUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setDni("12345678A");
        usuario1.setNombre("Juan Pérez");
        usuario1.setEmail("juan@example.com");
        usuario1.setPassword("password123");
        usuario1.setTipo("Usuario");
        usuario1.setPenalizacionHasta(null);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setDni("87654321B");
        usuario2.setNombre("Ana López");
        usuario2.setEmail("ana@example.com");
        usuario2.setPassword("password456");
        usuario2.setTipo("Admin");
        usuario2.setPenalizacionHasta(LocalDate.now().plusDays(10));

        when(usuariosRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Act
        ResponseEntity response = crudUsuario.get();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Arrays.asList(usuario1, usuario2), response.getBody());
        verify(usuariosRepository, times(1)).findAll();
    }

    @Test
    void getUsuarioById() {
        // Arrange
        String id = "1";
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setDni("12345678A");
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan@example.com");
        usuario.setPassword("password123");
        usuario.setTipo("Usuario");

        when(usuariosRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Act
        ResponseEntity response = crudUsuario.get(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(usuariosRepository, times(1)).findById(id);
    }

    @Test
    void addUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(3);
        usuario.setDni("12312312C");
        usuario.setNombre("Carlos Gómez");
        usuario.setEmail("carlos@example.com");
        usuario.setPassword("password789");
        usuario.setTipo("Usuario");

        when(usuariosRepository.save(usuario)).thenReturn(usuario);

        // Act
        ResponseEntity response = crudUsuario.post(usuario);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(usuariosRepository, times(1)).save(usuario);
    }

    @Test
    void updateUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setDni("12345678A");
        usuario.setNombre("Juan Pérez Modificado");
        usuario.setEmail("juan.modificado@example.com");
        usuario.setPassword("newpassword123");
        usuario.setTipo("Usuario");

        when(usuariosRepository.save(usuario)).thenReturn(usuario);

        // Act
        ResponseEntity response = crudUsuario.update(usuario);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(usuariosRepository, times(1)).save(usuario);
    }

    @Test
    void deleteUsuario() {
        // Arrange
        String id = "1";
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setDni("12345678A");
        usuario.setNombre("Juan Pérez");

        when(usuariosRepository.findById(id)).thenReturn(Optional.of(usuario));
        doNothing().when(usuariosRepository).delete(usuario);

        // Act
        ResponseEntity response = crudUsuario.delete(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(usuariosRepository, times(1)).findById(id);
        verify(usuariosRepository, times(1)).delete(usuario);
    }
}