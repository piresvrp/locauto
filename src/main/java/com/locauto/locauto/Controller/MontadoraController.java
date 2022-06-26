package com.locauto.locauto.Controller;

import com.locauto.locauto.model.entity.Montadora;
import com.locauto.locauto.model.repository.MontadoraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/montadora")
public class MontadoraController {


    private MontadoraRepository montadoraRepository;

    public MontadoraController(MontadoraRepository montadoraRepository){
        this.montadoraRepository = montadoraRepository;
    }

    //Lista de Montadoras
    @GetMapping
    public List<Montadora> list(){

        return montadoraRepository.findAll();
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Montadora> show(@PathVariable long id){

        Optional<Montadora> optional = montadoraRepository.findById(id);

        if(optional.isPresent()){
            return new ResponseEntity<Montadora>(optional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //create
    @PostMapping
    public Montadora save(@RequestBody @Valid Montadora montadora){
        return montadoraRepository.save(montadora);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Montadora> delete(@PathVariable Long id){
        try {
            montadoraRepository.deleteById(id);
            return new ResponseEntity<Montadora>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<Montadora>(HttpStatus.NOT_FOUND);
        }

    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Montadora> update(@PathVariable Long id, @RequestBody @Valid Montadora montadora){
        //Busca plano pelo id

        Optional<Montadora> optional = montadoraRepository.findById(id);
        if(optional.isPresent()){
            Montadora  montadoraAux = optional.get();
            montadoraAux.setNome(montadora.getNome());
            return  new ResponseEntity<Montadora>(montadoraRepository.save(montadoraAux), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
