package org.example.crudbiblioteca.Controladores;

import org.example.crudbiblioteca.DTO.Ejemplar;
import org.example.crudbiblioteca.Interfaces.EjemplaresRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejemplar")
public class CRUDEJEMPLAR {
    EjemplaresRepository ejemplaresRepository;

    public CRUDEJEMPLAR(EjemplaresRepository ejemplaresRepository) {
        this.ejemplaresRepository = ejemplaresRepository;
    }

    //GETTER QUE DEVUELVE TODOS LOS EJEMPLARES DE LA BBDD
    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(ejemplaresRepository.findAll());
    }

    //GETTER DE UN EJEMPLAR SOLO PASANDO SU ISBN
    @PostMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        Ejemplar l = ejemplaresRepository.findById(id).get();
        return ResponseEntity.ok(l);
    }

    //AÑADIR UN EJEMPLAR CON PARAMETROS
    @PostMapping
    public ResponseEntity post(@RequestBody Ejemplar ejemplar) {
        ejemplaresRepository.save(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }

    //MODIFICAR UN EJEMPLAR CON PARÁMETROS
    @PutMapping
    public ResponseEntity update(@RequestBody Ejemplar ejemplar) {
        ejemplaresRepository.save(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) {
        Ejemplar ejemplar = ejemplaresRepository.findById(isbn).get();
        ejemplaresRepository.delete(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }
}

