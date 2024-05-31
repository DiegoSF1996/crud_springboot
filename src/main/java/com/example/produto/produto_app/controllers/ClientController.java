package com.example.produto.produto_app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.produto.produto_app.dtos.ClientRecordDto;
import com.example.produto.produto_app.models.ClientModel;
import com.example.produto.produto_app.repositories.ClientRepositoy;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ClientController {

    @Autowired
    ClientRepositoy clientRepositoy;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> index(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientRepositoy.findByNameUnaccent("%"+search+"%"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepositoy.findAll());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> show(@PathVariable(value="id") UUID id) {
        Optional<ClientModel> client0 = clientRepositoy.findById(id);
        if(client0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(client0.get());
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientModel> saveProduct(@RequestBody @Valid ClientRecordDto clientRecordDto) {
        //var clientModel = new ClientModel();
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepositoy.save(clientModel));
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> update(@PathVariable(value="id") UUID id, @RequestBody @Valid ClientRecordDto clientRecordDto) {
        Optional<ClientModel> client0 = clientRepositoy.findById(id);
        if(client0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var clientModel = client0.get();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepositoy.save(clientModel));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> update(@PathVariable(value="id") UUID id) {
        Optional<ClientModel> client0 = clientRepositoy.findById(id);
        if(client0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        clientRepositoy.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");
    }
    
}
