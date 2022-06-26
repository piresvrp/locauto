package com.locauto.locauto.Controller;

import com.locauto.locauto.model.entity.Montadora;
import com.locauto.locauto.model.entity.Veiculo;
import com.locauto.locauto.model.repository.MontadoraRepository;
import com.locauto.locauto.model.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

       private VeiculoRepository veiculoRepository;


       public VeiculoController(VeiculoRepository veiculoRepository){
           this.veiculoRepository = veiculoRepository;
       }


    //Lista de veiculos
    @GetMapping
    public List<Veiculo> list(){
        return veiculoRepository.findAll();
    }
       //

    //red
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> show(@PathVariable long id){
        Optional<Veiculo> optional = veiculoRepository.findById(id);
        if(optional.isPresent()){
            return new ResponseEntity<Veiculo>(optional.get(), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //create
    @PostMapping
    public Veiculo save(@RequestBody @Valid Veiculo veiculo){
           return veiculoRepository.save(veiculo);
    }

    //create
    /*   @PostMapping
    public Montadora save(@RequestBody @Valid Montadora montadora){
        return montadoraRepository.save(montadora);
    }*/


    //update
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable long id, @RequestBody @Valid Veiculo veiculo){
           Optional<Veiculo> optional = veiculoRepository.findById(id);

           if(optional.isPresent()){
                Veiculo veiculoAux = optional.get();
                veiculoAux.setAno(veiculo.getAno());
                veiculoAux.setCor(veiculo.getCor());
                veiculoAux.setPlaca(veiculo.getPlaca());
                veiculoAux.setModelo(veiculo.getModelo());
               veiculoAux.setNome(veiculo.getNome());
               return new ResponseEntity<Veiculo>(veiculoRepository.save(veiculoAux), HttpStatus.OK);
           }else{
               return new ResponseEntity<Veiculo>(HttpStatus.NOT_FOUND);
           }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Veiculo> delete(@PathVariable Long id){
        try {
            veiculoRepository.deleteById(id);
            return new ResponseEntity<Veiculo>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<Veiculo>(HttpStatus.NOT_FOUND);
        }

    }

}
