package org.example.crudbiblioteca;

import org.example.crudbiblioteca.Controladores.CRUDPRESTAMO;
import org.example.crudbiblioteca.DTO.Prestamo;
import org.example.crudbiblioteca.Interfaces.PrestamosRepository;
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

class CRUDPRESTAMOTest {

    @Mock
    private PrestamosRepository prestamosRepository;

    @InjectMocks
    private CRUDPRESTAMO crudPrestamo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPrestamos() {
        // Arrange
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setId(1);
        prestamo1.setFechaInicio(LocalDate.now());
        prestamo1.setFechaDevolucion(LocalDate.now().plusDays(7));

        Prestamo prestamo2 = new Prestamo();
        prestamo2.setId(2);
        prestamo2.setFechaInicio(LocalDate.now());
        prestamo2.setFechaDevolucion(LocalDate.now().plusDays(14));

        when(prestamosRepository.findAll()).thenReturn(Arrays.asList(prestamo1, prestamo2));

        // Act
        ResponseEntity response = crudPrestamo.get();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Arrays.asList(prestamo1, prestamo2), response.getBody());
        verify(prestamosRepository, times(1)).findAll();
    }

    @Test
    void getPrestamoById() {
        // Arrange
        String id = "1";
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1);
        prestamo.setFechaInicio(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));

        when(prestamosRepository.findById(id)).thenReturn(Optional.of(prestamo));

        // Act
        ResponseEntity response = crudPrestamo.get(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(prestamo, response.getBody());
        verify(prestamosRepository, times(1)).findById(id);
    }

    @Test
    void addPrestamo() {
        // Arrange
        Prestamo prestamo = new Prestamo();
        prestamo.setId(3);
        prestamo.setFechaInicio(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(10));

        when(prestamosRepository.save(prestamo)).thenReturn(prestamo);

        // Act
        ResponseEntity response = crudPrestamo.post(prestamo);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(prestamo, response.getBody());
        verify(prestamosRepository, times(1)).save(prestamo);
    }

    @Test
    void updatePrestamo() {
        // Arrange
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1);
        prestamo.setFechaInicio(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(15));

        when(prestamosRepository.save(prestamo)).thenReturn(prestamo);

        // Act
        ResponseEntity response = crudPrestamo.update(prestamo);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(prestamo, response.getBody());
        verify(prestamosRepository, times(1)).save(prestamo);
    }

    @Test
    void deletePrestamo() {
        // Arrange
        String id = "1";
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1);
        prestamo.setFechaInicio(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));

        when(prestamosRepository.findById(id)).thenReturn(Optional.of(prestamo));
        doNothing().when(prestamosRepository).delete(prestamo);

        // Act
        ResponseEntity response = crudPrestamo.delete(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(prestamo, response.getBody());
        verify(prestamosRepository, times(1)).findById(id);
        verify(prestamosRepository, times(1)).delete(prestamo);
    }
}
