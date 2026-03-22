package com.bn.library.controllers;

import com.bn.library.models.LivroModel;
import com.bn.library.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroModel> criarLivro(@RequestBody LivroModel livroModel) {
        LivroModel request = livroService.criarLivro(livroModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<LivroModel>> findAll() {
        List<LivroModel> request = livroService.findAll();
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroModel> buscarPorId(@PathVariable Long id) {
        Optional<LivroModel> request = livroService.buscarPorId(id);

        if(request.isPresent()) {
            return ResponseEntity.ok(request.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable Long id) {
        Optional<LivroModel> request = livroService.buscarPorId(id);

        if (request.isPresent()) {
            livroService.deletarLivro(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
