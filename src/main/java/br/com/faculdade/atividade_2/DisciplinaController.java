package br.com.faculdade.atividade_2;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final Map<Long, Disciplina> repo = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public DisciplinaController() {
        Disciplina d1 = new Disciplina(idCounter.incrementAndGet(), "Programação Web", "DSM", 60);
        Disciplina d2 = new Disciplina(idCounter.incrementAndGet(), "Engenharia de Software", "DSM", 80);
        repo.put(d1.getId(), d1);
        repo.put(d2.getId(), d2);
    }

    @GetMapping
    public List<Disciplina> listar() {
        List<Disciplina> list = new ArrayList<>(repo.values());
        Collections.sort(list, (a, b) -> a.getId().compareTo(b.getId()));
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable("id") Long id) {
        Disciplina d = repo.get(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(d);
    }

    @PostMapping
    public ResponseEntity<Disciplina> criar(@RequestBody Disciplina disciplina) {
        long id = idCounter.incrementAndGet();
        disciplina.setId(id);
        repo.put(id, disciplina);
        URI uri = URI.create(String.format("/disciplinas/%d", id));
        return ResponseEntity.created(uri).body(disciplina);
    }
}
