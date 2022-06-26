package com.locauto.locauto.Controller;

import com.locauto.locauto.model.entity.Aluguel;
import com.locauto.locauto.model.entity.Cliente;
import com.locauto.locauto.model.entity.Montadora;
import com.locauto.locauto.model.repository.AluguelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {

    private AluguelRepository aluguelRepository;

    public AluguelController(AluguelRepository aluguelRepository){
        this.aluguelRepository = aluguelRepository;
    }

    //Lista de Alugies
    @GetMapping
    public List<Aluguel> list(){
        return aluguelRepository.findAll();
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> show(@PathVariable long id){
        Optional<Aluguel> optional = aluguelRepository.findById(id);
        if(optional.isPresent()){
            return new ResponseEntity<Aluguel>(optional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //create
    @PostMapping
    public Aluguel save(@RequestBody @Valid Aluguel aluguel){
        return aluguelRepository.save(aluguel);
    }


    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Aluguel> delete(@PathVariable Long id){
        try {
            aluguelRepository.deleteById(id);
            return new ResponseEntity<Aluguel>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<Aluguel>(HttpStatus.NOT_FOUND);
        }
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> update(@PathVariable Long id, @RequestBody @Valid Aluguel aluguel){
        //Busca plano pelo id

        Optional<Aluguel> optional = aluguelRepository.findById(id);
        if(optional.isPresent()){
            Aluguel  aluguelaux = optional.get();
            aluguelaux.setDataDeDevolucao(aluguel.getDataDeDevolucao());
            aluguelaux.setDatadDeLocalocao(aluguel.getDatadDeLocalocao());
            aluguel.setKmVeiculo(aluguel.getKmVeiculo());
            return  new ResponseEntity<Aluguel>(aluguelRepository.save(aluguelaux), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
