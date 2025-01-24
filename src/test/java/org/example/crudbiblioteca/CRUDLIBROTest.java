package org.example.crudbiblioteca;
import org.example.crudbiblioteca.Controladores.CRUDLIBRO;
import org.example.crudbiblioteca.DTO.Libro;
import org.example.crudbiblioteca.Interfaces.LibrosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CRUDLIBROTest {

    @Mock
    private LibrosRepository librosRepository;

    @InjectMocks
    private CRUDLIBRO crudLibro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLibros() {
        // Arrange
        Libro libro1 = new Libro();
        libro1.setIsbn("123");
        libro1.setTitulo("Libro 1");
        libro1.setAutor("Autor 1");

        Libro libro2 = new Libro();
        libro2.setIsbn("456");
        libro2.setTitulo("Libro 2");
        libro2.setAutor("Autor 2");

        when(librosRepository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        // Act
        ResponseEntity response = crudLibro.get();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Arrays.asList(libro1, libro2), response.getBody());
        verify(librosRepository, times(1)).findAll();
    }

    @Test
    void getLibroByIsbn() {
        // Arrange
        String isbn = "123";
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo("Libro 1");
        libro.setAutor("Autor 1");

        when(librosRepository.findById(isbn)).thenReturn(Optional.of(libro));

        // Act
        ResponseEntity response = crudLibro.get(isbn);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(librosRepository, times(1)).findById(isbn);
    }

    @Test
    void addLibro() {
        // Arrange
        Libro libro = new Libro();
        libro.setIsbn("789");
        libro.setTitulo("Nuevo Libro");
        libro.setAutor("Nuevo Autor");

        when(librosRepository.save(libro)).thenReturn(libro);

        // Act
        ResponseEntity response = crudLibro.post(libro);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(librosRepository, times(1)).save(libro);
    }

    @Test
    void updateLibro() {
        // Arrange
        Libro libro = new Libro();
        libro.setIsbn("123");
        libro.setTitulo("Libro Actualizado");
        libro.setAutor("Autor Actualizado");

        when(librosRepository.save(libro)).thenReturn(libro);

        // Act
        ResponseEntity response = crudLibro.update(libro);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(librosRepository, times(1)).save(libro);
    }

    @Test
    void deleteLibro() {
        // Arrange
        String isbn = "123";
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo("Libro a Eliminar");
        libro.setAutor("Autor a Eliminar");

        when(librosRepository.findById(isbn)).thenReturn(Optional.of(libro));
        doNothing().when(librosRepository).delete(libro);

        // Act
        ResponseEntity response = crudLibro.delete(isbn);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(libro, response.getBody());
        verify(librosRepository, times(1)).findById(isbn);
        verify(librosRepository, times(1)).delete(libro);
    }
}