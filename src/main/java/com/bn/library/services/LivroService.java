package com.bn.library.services;

import com.bn.library.models.LivroModel;
import com.bn.library.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroModel> findAll() {
        return livroRepository.findAll();
    }

    public Optional<LivroModel> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public LivroModel criarLivro(LivroModel livroModel) {
        return livroRepository.save(livroModel);
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
    }
}
