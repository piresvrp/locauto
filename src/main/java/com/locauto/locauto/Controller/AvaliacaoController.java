package com.locauto.locauto.Controller;
import com.locauto.locauto.model.entity.Avaliacao;
import com.locauto.locauto.model.repository.AvaliacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository){
        this.avaliacaoRepository = avaliacaoRepository;
    }

    //Lista de Cliente
    @GetMapping
    public List<Avaliacao> list(){

        return avaliacaoRepository.findAll();
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> show(@PathVariable long id){
        Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
        if(optional.isPresent()){
            return new ResponseEntity<Avaliacao>(optional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //create
    @PostMapping
    public Avaliacao save(@RequestBody @Valid Avaliacao avaliacao){
        return avaliacaoRepository.save(avaliacao);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Avaliacao> delete(@PathVariable Long id){
        try {
            avaliacaoRepository.deleteById(id);
            return new ResponseEntity<Avaliacao>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<Avaliacao>(HttpStatus.NOT_FOUND);
        }

    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> update(@PathVariable Long id, @RequestBody @Valid Avaliacao avaliacao){
        Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
        if(optional.isPresent()){
            Avaliacao avaliacaoAux = optional.get();
            avaliacaoAux.setDataAvalicao(avaliacao.getDataAvalicao());
            avaliacaoAux.setKmentrega(avaliacao.getKmentrega());
            avaliacaoAux.setPrecoFinal(avaliacao.getPrecoFinal());
            return  new ResponseEntity<Avaliacao>(avaliacaoRepository.save(avaliacaoAux), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
