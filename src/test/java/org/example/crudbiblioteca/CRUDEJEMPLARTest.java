package org.example.crudbiblioteca;

import org.example.crudbiblioteca.Controladores.CRUDEJEMPLAR;
import org.example.crudbiblioteca.DTO.Ejemplar;
import org.example.crudbiblioteca.Interfaces.EjemplaresRepository;
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

class CRUDEJEMPLARTest {

    @Mock
    private EjemplaresRepository ejemplaresRepository;

    @InjectMocks
    private CRUDEJEMPLAR crudEjemplar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEjemplares() {
        // Arrange
        Ejemplar ejemplar1 = new Ejemplar();
        ejemplar1.setId(1);
        ejemplar1.setEstado("Disponible");

        Ejemplar ejemplar2 = new Ejemplar();
        ejemplar2.setId(2);
        ejemplar2.setEstado("No disponible");

        when(ejemplaresRepository.findAll()).thenReturn(Arrays.asList(ejemplar1, ejemplar2));

        // Act
        ResponseEntity response = crudEjemplar.get();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Arrays.asList(ejemplar1, ejemplar2), response.getBody());
        verify(ejemplaresRepository, times(1)).findAll();
    }

    @Test
    void getEjemplarById() {
        // Arrange
        String id = "1";
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setId(1);
        ejemplar.setEstado("Disponible");

        when(ejemplaresRepository.findById(id)).thenReturn(Optional.of(ejemplar));

        // Act
        ResponseEntity response = crudEjemplar.get(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplar, response.getBody());
        verify(ejemplaresRepository, times(1)).findById(id);
    }

    @Test
    void addEjemplar() {
        // Arrange
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setId(3);
        ejemplar.setEstado("Disponible");

        when(ejemplaresRepository.save(ejemplar)).thenReturn(ejemplar);

        // Act
        ResponseEntity response = crudEjemplar.post(ejemplar);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplar, response.getBody());
        verify(ejemplaresRepository, times(1)).save(ejemplar);
    }

    @Test
    void updateEjemplar() {
        // Arrange
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setId(3);
        ejemplar.setEstado("Actualizado");

        when(ejemplaresRepository.save(ejemplar)).thenReturn(ejemplar);

        // Act
        ResponseEntity response = crudEjemplar.update(ejemplar);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplar, response.getBody());
        verify(ejemplaresRepository, times(1)).save(ejemplar);
    }

    @Test
    void deleteEjemplar() {
        // Arrange
        String id = "1";
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setId(1);
        ejemplar.setEstado("Disponible");

        when(ejemplaresRepository.findById(id)).thenReturn(Optional.of(ejemplar));
        doNothing().when(ejemplaresRepository).delete(ejemplar);

        // Act
        ResponseEntity response = crudEjemplar.delete(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ejemplar, response.getBody());
        verify(ejemplaresRepository, times(1)).findById(id);
        verify(ejemplaresRepository, times(1)).delete(ejemplar);
    }
}

