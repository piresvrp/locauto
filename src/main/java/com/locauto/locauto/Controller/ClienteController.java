package com.locauto.locauto.Controller;

import com.locauto.locauto.model.entity.Cliente;
import com.locauto.locauto.model.entity.Montadora;
import com.locauto.locauto.model.repository.ClienteRepository;
import com.locauto.locauto.model.repository.MontadoraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //Lista de Cliente
    @GetMapping
    public List<Cliente> list(){
        return clienteRepository.findAll();
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> show(@PathVariable long id){

        Optional<Cliente> optional = clienteRepository.findById(id);

        if(optional.isPresent()){
            return new ResponseEntity<Cliente>(optional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //create
    @PostMapping
    public Cliente save(@RequestBody @Valid Cliente cliente){

        return clienteRepository.save(cliente);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id){
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }

    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
        Optional<Cliente> optional = clienteRepository.findById(id);
        if(optional.isPresent()){
            Cliente  clienteAux = optional.get();
            clienteAux.setNome(cliente.getNome());
            clienteAux.setCpf(cliente.getCpf());
            clienteAux.setCnh(cliente.getCnh());
            clienteAux.setDataDeNascimento(cliente.getDataDeNascimento());
            clienteAux.setEndereco(cliente.getEndereco());
            clienteAux.setCidade(cliente.getCidade());
            clienteAux.setEstado(cliente.getEstado());
            return  new ResponseEntity<Cliente>(clienteRepository.save(clienteAux), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
