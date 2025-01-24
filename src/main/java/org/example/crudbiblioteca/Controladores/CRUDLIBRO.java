package org.example.crudbiblioteca.Controladores;

import org.example.crudbiblioteca.DTO.Libro;
import org.example.crudbiblioteca.Interfaces.LibrosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libro")
public class CRUDLIBRO {
    LibrosRepository librosRepository;

    public CRUDLIBRO(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }

    //GETTER QUE DEVUELVE TODOS LOS LIRBOS DE LA BBDD
    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(librosRepository.findAll());
    }

    //GETTER DE UN LIBRO SOLO PASANDO SU ISBN
    @PostMapping("/{isbn}")
    public ResponseEntity get(@PathVariable String isbn) {
        Libro l = librosRepository.findById(isbn).get();
        return ResponseEntity.ok(l);
    }

    //AÑADIR UN LIBRO CON PARAMETROS
    @PostMapping
    public ResponseEntity post(@RequestBody Libro libro) {
        librosRepository.save(libro);
        return ResponseEntity.ok(libro);
    }

    //MODIFICAR UN LIBRO CON PARÁMETROS
    @PutMapping
    public ResponseEntity update(@RequestBody Libro libro) {
        librosRepository.save(libro);
        return ResponseEntity.ok(libro);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) {
        Libro libro = librosRepository.findById(isbn).get();
        librosRepository.delete(libro);
        return ResponseEntity.ok(libro);
    }
}
