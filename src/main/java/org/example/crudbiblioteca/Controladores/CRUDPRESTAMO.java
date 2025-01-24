package org.example.crudbiblioteca.Controladores;

import org.example.crudbiblioteca.DTO.Prestamo;
import org.example.crudbiblioteca.Interfaces.PrestamosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamo")
public class CRUDPRESTAMO {
    PrestamosRepository prestamoRepository;

    public CRUDPRESTAMO(PrestamosRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    //GETTER QUE DEVUELVE TODOS LOS LIRBOS DE LA BBDD
    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(prestamoRepository.findAll());
    }

    //GETTER DE UN PRESTAMO SOLO PASANDO SU ISBN
    @PostMapping("/{isbn}")
    public ResponseEntity get(@PathVariable String isbn) {
        Prestamo l = prestamoRepository.findById(isbn).get();
        return ResponseEntity.ok(l);
    }

    //AÑADIR UN PRESTAMO CON PARAMETROS
    @PostMapping
    public ResponseEntity post(@RequestBody Prestamo prestamo) {
        prestamoRepository.save(prestamo);
        return ResponseEntity.ok(prestamo);
    }

    //MODIFICAR UN PRESTAMO CON PARÁMETROS
    @PutMapping
    public ResponseEntity update(@RequestBody Prestamo prestamo) {
        prestamoRepository.save(prestamo);
        return ResponseEntity.ok(prestamo);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) {
        Prestamo prestamo = prestamoRepository.findById(isbn).get();
        prestamoRepository.delete(prestamo);
        return ResponseEntity.ok(prestamo);
    }
}
