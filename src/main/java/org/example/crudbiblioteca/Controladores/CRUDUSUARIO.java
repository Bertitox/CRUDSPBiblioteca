package org.example.crudbiblioteca.Controladores;

import jakarta.validation.Valid;
import org.example.crudbiblioteca.DTO.Libro;
import org.example.crudbiblioteca.DTO.Usuario;
import org.example.crudbiblioteca.Interfaces.UsuariosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class CRUDUSUARIO {
    UsuariosRepository usuariosRepository;

    public CRUDUSUARIO(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    //GETTER QUE DEVUELVE TODOS LOS USUARIOS DE LA BBDD
    //http://localhost:8090/usuario/get
    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(usuariosRepository.findAll());
    }

    //GETTER DE UN USUARIO SOLO PASANDO SU ID
    @PostMapping("/{id}")
    public ResponseEntity get(@Valid @PathVariable String id) {
        Usuario l = usuariosRepository.findById(id).get();
        return ResponseEntity.ok(l);
    }

    //AÑADIR UN USUARIO CON PARAMETROS
    @PostMapping
    public ResponseEntity post(@Valid @RequestBody Usuario usuario) {
        usuariosRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    //MODIFICAR UN USUARIO CON PARÁMETROS
    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Usuario usuario) {
        usuariosRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam String id) {
        Usuario usuario = usuariosRepository.findById(id).get();
        usuariosRepository.delete(usuario);
        return ResponseEntity.ok(usuario);
    }

}
