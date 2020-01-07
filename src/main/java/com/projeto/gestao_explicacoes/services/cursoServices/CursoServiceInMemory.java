package com.projeto.gestao_explicacoes.services.cursoServices;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "inmemory")
public class CursoServiceInMemory implements CursoService {

    @Override
    public Set<Curso> findAll() {
        return null;
    }

    @Override
    public Optional<Curso> criarCursoFaculdade(Curso curso, Faculdade faculdade) {

        return Optional.empty();
    }
}